package com.app.dc.service.job;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.app.common.utils.JsonUtils;
import com.app.dc.entity.*;
import com.app.dc.util.Consts;
import org.apache.commons.lang3.StringUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.BeanUtils;

import com.app.common.db.DBUtils;
import com.app.common.utils.IdUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class KolJob implements Job {

	public boolean runFlag = false;

	int retryCount = 3;
	int currentCount = 1;
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		log.info("KolJob execute");
		try {
			kolJob(null, Consts.Kol_Batch);
			currentCount = 1;
		} catch (Exception e) {
			log.error(e+e.getMessage());
			if (currentCount <= retryCount){
				log.info("KolJob Re execute,currentCount:{}",currentCount);
				runFlag = false;
				currentCount = currentCount + 1;
				execute(null);
			}

		}
	}

	public synchronized void kolJob(String dateStr, String type) throws Exception {
		if (!runFlag) {
			runFlag = true;
			usersRptKolJob(dateStr, type);

			runFlag = false;
		} else {
			log.warn("KolJob job is runing.");

		}

	}

	private void usersRptKolJob(String dateStr, String type) throws Exception {
		log.info("usersRptKolJob dateStr:{} type:{}", dateStr, type);
		HashMap<String, TUsers> hmUsers = new HashMap<>();
		HashMap<String, Integer> hmReferrerUsers = new HashMap<>();
		String date = getDate(dateStr, type);
		String lastDate = getLastDate(dateStr, type);
		List<TUsers> users = getAllUsers(date, type);
		log.info("usersRptKolJob date:{} lastDate:{}", date, lastDate);
		List<AirdropActivityDate> airdropActivityDates = JobDao.getAirdropActivityDate();
		for (TUsers tUsers : users) {
			hmUsers.put(tUsers.user_id, tUsers);
			if (StringUtils.isNoneBlank(tUsers.referrer)) {
				Integer count = hmReferrerUsers.get(tUsers.referrer);
				if (count != null) {
					count = count + 1;
				} else {
					count = 1;
				}
				hmReferrerUsers.put(tUsers.referrer, count);

			}
		}

		HashMap<String, TUsersRpt> hmUserRpt = getAllUsersRpt(lastDate);
		HashMap<String, BigDecimal> totalAssetsMap = new HashMap<>(); 
		if (hmUserRpt != null) {
			for (Map.Entry<String, TUsersRpt> entry : hmUserRpt.entrySet()) {
				totalAssetsMap.put(entry.getKey(), entry.getValue().total_assets);
			}
		}
		HashMap<String, TUsersBalance> hmUserBalance = getUsersBalance(date, type);
		List<TOrdersPosition> tOrdersPositions = getTOrdersPosition(date, type);
		HashMap<String, TMarkPriceDay> hmTMarkPriceDay = getTMarkPriceDay(date);
//
//		HashMap<String, BigDecimal> hmUserCashInOut = getAllUsersSumCashInOut(null);
//		HashMap<String, TOrdersExecorders> hmUserExecOrder = getAllUsersSumExecOrder(null);

		HashMap<String, BigDecimal> hmUserCashInOutDate = getAllUsersSumCashInOut(date);

		HashMap<String, TOrdersExecorders> hmUserExecOrderDate = getAllUsersSumExecOrder(date);

		HashMap<String, TOrdersExecorders> hmUserExecFeeOrderDate = getAllUsersSumExecFeeOrder(date);

		HashSet<String> cashInMark = getUserIdNumberCashInMark();
		HashSet<String> tradeMark = getUserIdNumberTradeMark();
		HashMap<String, String> hmUsersFirstDepositTime = getUsersFirstDepositTime();
		String lastMonth = getMonth(lastDate);
		HashMap<String, TKolRptMonth> hmKolRptMonth = getKolRptMonth(lastMonth);
		HashMap<String, TKolRuleDetail> hmKolRule = getKolRules();
		HashMap<String, TKolPartner> hmpartners = getAllTKolPartner(date, type);

		List<TOrdersExecorders> userExecOrder = new ArrayList<TOrdersExecorders>();
		userExecOrder.addAll(hmUserExecOrderDate.values());

		List<TOrdersExecorders> userExecFeeOrder = new ArrayList<TOrdersExecorders>();
		userExecFeeOrder.addAll(hmUserExecFeeOrderDate.values());

		HashMap<String, TKolRptDay> hmTKolRptDay = new HashMap<>();
		HashMap<String, TUsersRpt> hmTUsersRpt = new HashMap<>();
		HashMap<String, TUsersRptDay> hmTUsersRptDay = new HashMap<>();
		HashMap<String, BigDecimal> unsettled_plMap = new HashMap<>();
		calUnsettled_pl(tOrdersPositions, hmTMarkPriceDay, unsettled_plMap);
		for (TUsers user : users) {
			try {
				TKolRptDay day1 = null;
				TKolRptDay day2 = null;
				String user_id1 = user.referrer;
				if (StringUtils.isNoneBlank(user_id1)) {
					TKolPartner partner1 = hmpartners.get(user_id1);
					if (partner1 != null) {
						day1 = new TKolRptDay();
						day1.user_id = user.user_id;
						day1.kol_user_id = user_id1;// kol id
						day1.kol_time = partner1.kol_time;// kol
						day1.type = "1";
						day1.url = user.inf1;
						day1.id = IdUtil.getId();
						day1.trade_date = date;
						day1.first_login_time = user.create_time;
						if (user.create_time.contains(date)) {
							day1.invite_number = 1;
						}
						if (hmUserCashInOutDate.get(user.user_id + "_0") != null) {
							if (!cashInMark.contains(user.user_id)) {
								day1.invite_number_cash_in = 1;
							}
						}
						if (hmUserExecOrderDate.containsKey(user.user_id + "_6") || hmUserExecOrderDate.containsKey(user.user_id + "_7")) {
							if (!tradeMark.contains(user.user_id)) {
								day1.invite_number_trade = 1;
							}

						}
						hmTKolRptDay.put(day1.user_id + "_" + day1.type, day1);
					}

					TUsers users2 = hmUsers.get(user_id1);
					if (users2 != null) {
						String user_id2 = users2.referrer;
						if (StringUtils.isNoneBlank(user_id2)) {
							TKolPartner partner2 = hmpartners.get(user_id2);
							if (partner2 != null) {
								day2 = new TKolRptDay();
								day2.user_id = user.user_id;
								day2.kol_user_id = user_id2;
								day2.kol_time = partner2.kol_time;
								day2.type = "2";
								day2.url = user.inf1;
								day2.id = IdUtil.getId();
								day2.trade_date = date;
								day2.first_login_time = user.create_time;
								if (user.create_time.contains(date)) {
									day2.invite_number = 1;
								}
								if (hmUserCashInOutDate.get(user.user_id + "_0") != null) {
									if (!cashInMark.contains(user.user_id)) {
										day2.invite_number_cash_in = 1;
									}
								}
								if (hmUserExecOrderDate.containsKey(user.user_id + "_6") || hmUserExecOrderDate.containsKey(user.user_id + "_7")) { 
									if (!tradeMark.contains(user.user_id)) {
										day2.invite_number_trade = 1;
									}

								}
								hmTKolRptDay.put(day2.user_id + "_" + day2.type, day2);
							}
						}
					}
				}

				TUsersRpt rpt = hmUserRpt.get(user.user_id);
				if (rpt == null) {
					rpt = new TUsersRpt();
					rpt.user_id = user.user_id;
					rpt.first_login_time = user.create_time;
					rpt.create_time = getDateTime();

				}
				//insertLt.add(rpt);
				hmTUsersRpt.put(rpt.user_id, rpt);
				if (rpt != null) {
					BigDecimal cashIn = hmUserCashInOutDate.get(user.user_id + "_0");
					if (cashIn != null) {
						if (day1 != null) {
							day1.acc_deposit_amount = cashIn;
						}
						if (day2 != null) {
							day2.acc_deposit_amount = cashIn;
						}
						if (rpt.acc_deposit_amount == null) {
							rpt.acc_deposit_amount = cashIn;
						} else {
							rpt.acc_deposit_amount = rpt.acc_deposit_amount.add(cashIn);
						}
					}

					BigDecimal cashOut = hmUserCashInOutDate.get(user.user_id + "_1");
					if (cashOut != null) {
						if (day1 != null) {
							day1.acc_withdraw_amount = cashOut;
						}
						if (day2 != null) {
							day2.acc_withdraw_amount = cashOut;
						}
						if (rpt.acc_withdraw_amount == null) {
							rpt.acc_withdraw_amount = cashOut;
						} else {
							rpt.acc_withdraw_amount = rpt.acc_withdraw_amount.add(cashOut);
						}
					}

					TOrdersExecorders tec6 = hmUserExecOrderDate.get(user.user_id + "_6");
					calExecOrdersByDay(day1, day2, rpt, tec6);
					TOrdersExecorders tec7 = hmUserExecOrderDate.get(user.user_id + "_7");
					calExecOrdersByDay(day1, day2, rpt, tec7);

				}
				if (StringUtils.isBlank(rpt.first_deposit_time)) {
					if (hmUsersFirstDepositTime.containsKey(user.user_id)) {
						rpt.first_deposit_time = hmUsersFirstDepositTime.get(user.user_id);
					}

				}
				rpt.update_time = getDateTime();

				rpt.is_kol = hmpartners.get(rpt.user_id) != null ? "1" : "0";
				if (day1 != null) {
					day1.first_deposit_time = rpt.first_deposit_time;
				}
				if (day2 != null) {
					day2.first_deposit_time = rpt.first_deposit_time;
				}
				Integer count = hmReferrerUsers.get(rpt.user_id);
				if (count != null) {
					rpt.invite_number = count;
				}


				TUsersRptDay tUsersRptDay = new TUsersRptDay();
				tUsersRptDay.trade_date = date;
				tUsersRptDay.id = IdUtil.getId();
				tUsersRptDay.create_time = tUsersRptDay.update_time = getDateTime();
				BeanUtils.copyProperties(rpt, tUsersRptDay);
				hmTUsersRptDay.put(tUsersRptDay.user_id, tUsersRptDay);

			} catch (Exception e) {
				log.error("user_id:{}", user.user_id, e);
			}
		}


		HashMap<String, BigDecimal> rebateDayMap = new HashMap<>();
		kolServiceDetailJob(date, hmTKolRptDay, hmpartners, userExecOrder, hmUserRpt, hmUsers, userExecFeeOrder, hmKolRule, hmKolRptMonth, rebateDayMap);

		boolean activity1Present = ActivityIntegralUtils.isActivity1Present(airdropActivityDates);

		for (TUsers user : users) {
			TUsersRptDay usersRptDay2 = hmTUsersRptDay.get(user.user_id);
			TUsersRpt usersRpt2 = hmTUsersRpt.get(user.user_id);

			if (usersRptDay2 != null) {
				usersRptDay2.acc_transact_vol2 = new BigDecimal(0.00000000);
				usersRptDay2.invite_number2 = 0;
				if (activity1Present) {
					usersRptDay2.activity1_invite_number2 = 0;
					usersRptDay2.activity1_invite_number1 = 0;
					usersRptDay2.activity1_integral = 0;
				}
//				usersRptDay2.integral = 0;
			}
			if (usersRpt2 != null) {
				usersRpt2.acc_transact_vol2 = new BigDecimal(0.00000000);
				usersRpt2.invite_number2 = 0;
				if (activity1Present) {
					usersRpt2.activity1_invite_number2 = 0;
					usersRpt2.activity1_invite_number1 = 0;
					usersRpt2.activity1_integral = 0;
				}
//				usersRpt2.integral = 0;
			}
		}

		List<TKolRptDay> insertDayLt = new ArrayList<>();
		insertDayLt.addAll(hmTKolRptDay.values());
		deleteTKolRptDay(date);
		insertKolRptDay(insertDayLt);
		monUrlKolJob(date, hmpartners);


		monKolJob(date);

		List<TKolRpt> lt = getKolRptTotal();
		deleteTKolRpt();
		insertTKolRpt(lt);
		// kol acc_com_reb
		for (TKolRpt kolRpt: lt){
			if (hmTUsersRpt.containsKey(kolRpt.kol_user_id)){
				TUsersRpt rpt = hmTUsersRpt.get(kolRpt.kol_user_id);
				TUsersRptDay rptDay = hmTUsersRptDay.get(kolRpt.kol_user_id);
				rpt.acc_com_reb = kolRpt.acc_com_reb;
				rptDay.acc_com_reb = kolRpt.acc_com_reb;
			}
		}


		for (TUsers user : users) {
			String user_id1 = user.referrer;
			TUsers users1 = null;
			TUsers users2 = null;

			if (StringUtils.isNoneBlank(user_id1)) {
				users1 = hmUsers.get(user_id1);
				calInviteNumber2(user.user_id, user_id1, hmTUsersRpt, hmTUsersRptDay);
				if (users1 != null) {
					String user_id2 = users1.referrer;
					if (StringUtils.isNoneBlank(user_id2)) {
						users2 = hmUsers.get(user_id2);

					}
				}
				calActivityIntegral(users1, users2, user, airdropActivityDates, hmTUsersRpt, hmTUsersRptDay);
			}
			calPL(user, hmTUsersRpt, hmTUsersRptDay, hmUserBalance, unsettled_plMap, hmUserCashInOutDate, rebateDayMap, totalAssetsMap);

		}
		List<TUsersRpt> insertLt = new ArrayList<>();
		insertLt.addAll(hmTUsersRpt.values());
		ActivityIntegralUtils.updateActivityIntegral(insertLt, hmTUsersRptDay);
		List<TUsersRptDay> insertUsersRptDayLt = new ArrayList<>();
		insertUsersRptDayLt.addAll(hmTUsersRptDay.values());
		;JobDao.deleteTUsersRptDay(date);
		JobDao.insertUsersRptDay(insertUsersRptDayLt);


		JobDao.deleteTUsersRpt();
		JobDao.insertUsersRpt(insertLt);



		log.info("usersRptKolJob success");
	}


	private void calExecOrdersByDay(TKolRptDay day1, TKolRptDay day2, TUsersRpt rpt, TOrdersExecorders tec) {
		if (tec != null) {
			BigDecimal exec_value = tec.exec_value;
			BigDecimal closed_pnl = tec.closed_pnl;
			BigDecimal exec_fee = tec.exec_fee;
			String exec_type = tec.exec_type;
			if (day1 != null) {
				day1.acc_transact_vol = day1.acc_transact_vol.add(exec_value);
				day1.acc_transact_pl = day1.acc_transact_pl.add(closed_pnl);
				day1.acc_transact_fee = day1.acc_transact_fee.add(exec_fee);
			}
			if (day2 != null) {
				day2.acc_transact_vol = day2.acc_transact_vol.add(exec_value);
				day2.acc_transact_pl = day2.acc_transact_pl.add(closed_pnl);
				day2.acc_transact_fee = day2.acc_transact_fee.add(exec_fee);
			}
			rpt.acc_transact_vol = rpt.acc_transact_vol.add(exec_value);
			rpt.acc_transact_pl = rpt.acc_transact_pl.add(closed_pnl);
			rpt.acc_transact_fee = rpt.acc_transact_fee.add(exec_fee);
			if ("7".equals(exec_type)) {
				rpt.taker_volume = rpt.taker_volume.add(exec_value);
				if (day1 != null) {
					day1.taker_volume = day1.taker_volume.add(exec_value);
				}
				if (day2 != null) {
					day2.taker_volume = day2.taker_volume.add(exec_value);
				}
			}
			if ("6".equals(exec_type)) {
				rpt.maker_volume = rpt.maker_volume.add(exec_value);
				if (day1 != null) {
					day1.maker_volume = day1.maker_volume.add(exec_value);
				}
				if (day2 != null) {
					day2.maker_volume = day2.maker_volume.add(exec_value);
				}
			}
		}
	}

	public void kolServiceDetailJob(String date, HashMap<String, TKolRptDay> hmTKolRptDay,
									HashMap<String, TKolPartner> hmpartners, List<TOrdersExecorders> userExecOrder,
									HashMap<String, TUsersRpt> hmUserRpt, HashMap<String, TUsers> hmUsers, List<TOrdersExecorders> userExecFeeOrder, HashMap<String, TKolRuleDetail> hmKolRule, HashMap<String, TKolRptMonth> hmKolRptMonth, HashMap<String, BigDecimal> rebateDayMap) throws Exception {
		HashMap<String, TKolRptDetail> hmDetail = new HashMap<>();


		for (TOrdersExecorders exec : userExecOrder) {
			String user_id = exec.user_id;
			BigDecimal exec_value = exec.exec_value;
			//BigDecimal exec_fee = tOrdersExecorders.exec_fee;

			TUsers users = hmUsers.get(user_id);
			if (users != null) {
				String user_id1 = users.referrer;
				if (StringUtils.isNoneBlank(user_id1)) {
					TKolPartner tKolPartner = hmpartners.get(user_id1);
					if (tKolPartner != null) {
						BigDecimal direct_cus_ret_serv = tKolPartner.direct_cus_ret_serv;
						if (direct_cus_ret_serv != null) {
							TKolRptDetail detail = hmDetail.get(user_id + "_" + user_id1);
							if (detail == null) {
								detail = new TKolRptDetail();
								hmDetail.put(user_id + "_" + user_id1, detail);
								detail.id = IdUtil.getId();
								detail.trade_date = date;

								detail.user_id = user_id;
								detail.kol_user_id = user_id1;
								detail.type = "1";
								detail.percent = direct_cus_ret_serv;
								detail.status = "1";
								detail.volume = exec_value;
								//detail.amount = detail.percent.multiply(exec_fee);
								detail.update_time = detail.create_time = getDateTime();
							} else {
								detail.volume = detail.volume.add(exec_value);
								//detail.amount = detail.amount.add(detail.percent.multiply(exec_fee));
							}

						}

					}
					TUsers users2 = hmUsers.get(user_id1);
					if (users2 != null) {
						String user_id2 = users2.referrer;
						if (StringUtils.isNoneBlank(user_id2)) {
							TKolPartner tKolPartner2 = hmpartners.get(user_id2);
							if (tKolPartner2 != null) {
								BigDecimal second_com_prop = tKolPartner2.second_com_prop;

								if (second_com_prop != null) {
									TKolRptDetail detail = hmDetail.get(user_id + "_" + user_id2);
									if (detail == null) {
										detail = new TKolRptDetail();
										hmDetail.put(user_id + "_" + user_id2, detail);
										detail.id = IdUtil.getId();
										detail.trade_date = date;
										detail.type = "2";
										detail.kol_user_id = user_id2;
										detail.user_id = user_id;
										detail.percent = second_com_prop;
										detail.status = "1";
										detail.volume = exec_value;
										//detail.amount = detail.percent.multiply(exec_fee);
										detail.update_time = detail.create_time = getDateTime();
									} else {
										detail.volume = detail.volume.add(exec_value);
										//detail.amount = detail.amount.add(detail.percent.multiply(exec_fee));
									}
								}
							}
						}
					}
				}
			}
		}

		
		for (TOrdersExecorders execFee : userExecFeeOrder) {
			String user_id = execFee.user_id;
			BigDecimal exec_fee = execFee.exec_fee;
			TUsers users = hmUsers.get(user_id);
			if (users != null) {
				String user_id1 = users.referrer;
				if (StringUtils.isNoneBlank(user_id1)) {
					TKolPartner tKolPartner = hmpartners.get(user_id1);
					if (tKolPartner != null) {
						BigDecimal direct_cus_ret_serv = tKolPartner.direct_cus_ret_serv;
						
						if (checkAccComRebStatus(user_id1, tKolPartner.grade_name, hmKolRule, hmKolRptMonth)) {
							direct_cus_ret_serv = null;
						}
						if (direct_cus_ret_serv != null) {
							TKolRptDetail detail = hmDetail.get(user_id + "_" + user_id1);
							BigDecimal amount = detail.percent.multiply(exec_fee);
							if (detail.amount != null) {
								detail.amount = detail.amount.add(amount);
							} else {
								detail.amount = amount;
							}

							TKolRptDay day1 = hmTKolRptDay.get(user_id + "_1");
							if (day1 != null) {
								if (day1.acc_com_reb == null) {
									day1.acc_com_reb = detail.amount;
								} else {
									day1.acc_com_reb = day1.acc_com_reb.add(detail.amount);
								}
							}
							TUsersRpt userRpt = hmUserRpt.get(user_id1);
							if (userRpt != null) {
								BigDecimal rebate = rebateDayMap.get(user_id1);
								if (rebate == null) {
									rebate = amount;
								} else {
									rebate = rebate.add(amount);
								}
								rebateDayMap.put(user_id1, rebate);
								if (userRpt.acc_com_reb == null) {
									userRpt.acc_com_reb = detail.amount;
								} else {
									userRpt.acc_com_reb = userRpt.acc_com_reb.add(detail.amount);
								}
							}
						}

					}
					TUsers users2 = hmUsers.get(user_id1);
					if (users2 != null) {
						String user_id2 = users2.referrer;
						if (StringUtils.isNoneBlank(user_id2)) {
							TKolPartner tKolPartner2 = hmpartners.get(user_id2);
							if (tKolPartner2 != null) {
								BigDecimal second_com_prop = tKolPartner2.second_com_prop;


								if (checkAccComRebStatus(user_id2, tKolPartner2.grade_name, hmKolRule, hmKolRptMonth)) {
									second_com_prop = null;
								}


								if (second_com_prop != null) {
									TKolRptDetail detail = hmDetail.get(user_id + "_" + user_id2);
									BigDecimal amount2 = detail.percent.multiply(exec_fee);
									if (detail.amount != null) {
										detail.amount = detail.amount.add(amount2);
									} else {
										detail.amount = amount2;
									}


									TKolRptDay day2 = hmTKolRptDay.get(user_id + "_2");
									if (day2 != null) {
										if (day2.acc_com_reb == null) {
											day2.acc_com_reb = detail.amount;
										} else {
											day2.acc_com_reb = day2.acc_com_reb.add(detail.amount);
										}
									}
									TUsersRpt userRpt = hmUserRpt.get(user_id2);
									if (userRpt != null && "1".equals(userRpt.is_kol)) {
										BigDecimal rebate = rebateDayMap.get(user_id2);
										if (rebate == null) {
											rebate = amount2;
										} else {
											rebate = rebate.add(amount2);
										}
										rebateDayMap.put(user_id2, rebate);
										if (userRpt.acc_com_reb == null) {
											userRpt.acc_com_reb = detail.amount;
										} else {
											userRpt.acc_com_reb = userRpt.acc_com_reb.add(detail.amount);
										}
									}
								}
							}
						}
					}
				}
			}
		}
		deleteTKolRptDetail(date);
		List<TKolRptDetail> lt = new ArrayList<TKolRptDetail>();
		lt.addAll(hmDetail.values());
		insertTKolRptDetail(lt);
	}

	private boolean checkAccComRebStatus(String user_id, String grade_name, HashMap<String, TKolRuleDetail> hmKolRule, HashMap<String, TKolRptMonth> hmKolRptMonth) {
		boolean status = false;
		TKolRuleDetail kolRuleDetail = hmKolRule.get(grade_name);
		if (kolRuleDetail != null && kolRuleDetail.id.equals("1")) {
			TKolRptMonth tKolRptMonth = hmKolRptMonth.get(user_id);

			if (new BigDecimal(kolRuleDetail.total_inv_tran_vol_mon).compareTo(tKolRptMonth.total_inv_tran_vol_mon) > 0 || new BigDecimal(kolRuleDetail.total_inv_tran_num_mon).compareTo(tKolRptMonth.total_inv_tran_num_mon) > 0) {
				status = true;
			}
		}
		return status;
	}


	private void calActivityIntegral(TUsers users1, TUsers users2, TUsers user, List<AirdropActivityDate> airdropActivityDates, HashMap<String, TUsersRpt> hmTUsersRpt, HashMap<String, TUsersRptDay> hmTUsersRptDay) {
		boolean status = ActivityIntegralUtils.checkActivityIntegralDate(user.create_time,1, airdropActivityDates);
		if (status) {
			if (users1 != null) {
				TUsersRptDay users1RptDay = hmTUsersRptDay.get(users1.user_id);
				TUsersRpt users1Rpt = hmTUsersRpt.get(users1.user_id);
				users1Rpt.activity1_invite_number1 = users1Rpt.activity1_invite_number1 + 1;
				users1Rpt.activity1_integral = users1Rpt.activity1_integral + 5;
				users1Rpt.integral = users1Rpt.activity1_integral;


				users1RptDay.activity1_invite_number1 = users1Rpt.activity1_invite_number1;
				users1RptDay.activity1_integral = users1Rpt.activity1_integral;
				users1RptDay.integral = users1Rpt.integral;

			}
			if (users2 != null) {
				TUsersRpt users2Rpt = hmTUsersRpt.get(users2.user_id);
				users2Rpt.activity1_invite_number2 = users2Rpt.activity1_invite_number2 + 1;
				users2Rpt.activity1_integral = users2Rpt.activity1_integral + 2;
				users2Rpt.integral = users2Rpt.activity1_integral;


				TUsersRptDay users2RptDay = hmTUsersRptDay.get(users2.user_id);
				users2RptDay.activity1_invite_number2 = users2Rpt.activity1_invite_number2;
				users2RptDay.activity1_integral = users2Rpt.activity1_integral;
				users2RptDay.integral = users2Rpt.integral;

			}
		}
	}


	private void calInviteNumber2(String user_id,String user_id1,HashMap<String, TUsersRpt> hmTUsersRpt,HashMap<String, TUsersRptDay> hmTUsersRptDay){
		TUsersRptDay usersRptDay2 = hmTUsersRptDay.get(user_id); 
		TUsersRpt usersRpt2 = hmTUsersRpt.get(user_id); 
		TUsersRptDay usersRptDay = hmTUsersRptDay.get(user_id1);
		TUsersRpt usersRpt = hmTUsersRpt.get(user_id1);
		if (usersRpt2 != null && usersRpt != null){
			if (StringUtils.isEmpty(usersRpt.is_kol) || !"1".equals(usersRpt.is_kol)) {
				if (usersRpt2.acc_transact_vol != null) {
					usersRpt.acc_transact_vol2 = usersRpt.acc_transact_vol2.add(usersRpt2.acc_transact_vol);
				}
				usersRpt.invite_number2 = usersRpt.invite_number2 + usersRpt2.invite_number;
				if (usersRptDay2 != null && usersRptDay !=null){
					if (usersRptDay2.acc_transact_vol != null) {
						usersRptDay.acc_transact_vol2 = usersRptDay.acc_transact_vol2.add(usersRptDay2.acc_transact_vol);
					}
					usersRptDay.invite_number2 = usersRptDay.invite_number2 + usersRptDay2.invite_number;
				}
			}

		}

	}


	private void calUnsettled_pl(List<TOrdersPosition> tOrdersPositions,HashMap<String, TMarkPriceDay> hmTMarkPriceDay,HashMap<String,BigDecimal> plMap){

		if (tOrdersPositions != null) {
			for (TOrdersPosition tOrdersPosition : tOrdersPositions) {
				TMarkPriceDay tMarkPriceDay = hmTMarkPriceDay.get(tOrdersPosition.symbol);
				if (tMarkPriceDay != null) {
					BigDecimal unsettled_pl;
					if (tOrdersPosition.side == 1) {
						unsettled_pl = tOrdersPosition.size.multiply(tMarkPriceDay.mark_price.subtract(tOrdersPosition.avg_entry_px));
					} else {
						unsettled_pl = tOrdersPosition.size.multiply(tOrdersPosition.avg_entry_px.subtract(tMarkPriceDay.mark_price));
					}
					BigDecimal pl = plMap.get(tOrdersPosition.user_id);
					if (pl == null) {
						pl = unsettled_pl;
					} else {
						pl = pl.add(unsettled_pl);
					}
					plMap.put(tOrdersPosition.user_id, pl);

				}
			}
		}

	}

	private void calPL(TUsers user,HashMap<String, TUsersRpt> hmTUsersRpt,HashMap<String, TUsersRptDay> hmTUsersRptDay,HashMap<String, TUsersBalance> hmUserBalance,HashMap<String,BigDecimal> unsettled_plMap,HashMap<String, BigDecimal> hmUserCashInOutDate, HashMap<String, BigDecimal> rebateDayMap,HashMap<String, BigDecimal> totalAssetsMap) {
		TUsersRpt rpt = hmTUsersRpt.get(user.user_id);
		TUsersRptDay rptDay = hmTUsersRptDay.get(user.user_id);
		TUsersBalance tUsersBalance = hmUserBalance.get(user.user_id);
		if (tUsersBalance != null && rpt != null){


			BigDecimal acc_withdraw_amount = rpt.acc_withdraw_amount == null ? BigDecimal.ZERO :  rpt.acc_withdraw_amount;
			BigDecimal acc_deposit_amount = rpt.acc_deposit_amount == null ? BigDecimal.ZERO :  rpt.acc_deposit_amount;
			BigDecimal acc_com_reb = rpt.acc_com_reb == null ? BigDecimal.ZERO : rpt.acc_com_reb;
			BigDecimal account_balance = tUsersBalance.account_balance == null ? BigDecimal.ZERO : tUsersBalance.account_balance;
			BigDecimal lock_balance = tUsersBalance.lock_balance == null ? BigDecimal.ZERO : tUsersBalance.lock_balance;


			BigDecimal acc_pl = account_balance.subtract(acc_deposit_amount).add(acc_withdraw_amount).subtract(acc_com_reb);
			rpt.acc_pl = acc_pl.setScale(4, RoundingMode.DOWN);
			rptDay.acc_pl = rpt.acc_pl;


			BigDecimal divide = acc_deposit_amount.add(acc_com_reb);
			if (divide.compareTo(BigDecimal.ZERO) > 0) {
				rpt.acc_pl_ratio = rpt.acc_pl.divide(divide, 8, RoundingMode.DOWN);
                rpt.acc_pl_ratio = rpt.acc_pl_ratio.setScale(2, RoundingMode.DOWN);
				rptDay.acc_pl_ratio = rpt.acc_pl_ratio;
			}else {
				rpt.acc_pl_ratio = BigDecimal.ZERO;
				rptDay.acc_pl_ratio = rpt.acc_pl_ratio;
				log.debug("Divided by 0,user_id:{}",user.user_id);
			}

			BigDecimal unsettled_pl = unsettled_plMap.get(rpt.user_id);
			if (unsettled_pl == null){
				unsettled_pl = BigDecimal.ZERO;
			}
			rpt.total_assets = account_balance.add(unsettled_pl).add(lock_balance);
			rpt.total_assets = rpt.total_assets.setScale(2, RoundingMode.DOWN);
			rptDay.total_assets = rpt.total_assets;


			
			BigDecimal cashIn = hmUserCashInOutDate.get(user.user_id + "_0"); 
			BigDecimal cashOut = hmUserCashInOutDate.get(user.user_id + "_1"); 
			BigDecimal rebateDay = rebateDayMap.get(user.user_id);
			BigDecimal yesterdayTotal = totalAssetsMap.get(user.user_id);
			BigDecimal daily_acc_pl = rpt.total_assets;
			if (yesterdayTotal != null){
				daily_acc_pl = daily_acc_pl.subtract(yesterdayTotal);
			}
			if (cashIn != null){
				daily_acc_pl = daily_acc_pl.subtract(cashIn);
			}
			if (cashOut != null){
				daily_acc_pl = daily_acc_pl.add(cashOut);
			}
			if (rebateDay != null){
				daily_acc_pl = daily_acc_pl.subtract(rebateDay);
			}

			rpt.daily_acc_pl = daily_acc_pl.setScale(4, RoundingMode.DOWN);
			rptDay.daily_acc_pl = rpt.daily_acc_pl;
		}
	}

	private HashMap<String, String> getUsersFirstDepositTime() throws Exception {
		HashMap<String, String> hm = new HashMap<String, String>();
		String sql = "SELECT MIN(create_time) as create_time, user_id FROM dc_cash_log where side='0' and status='1'  group by user_id";
		List<TCashLog> cusLt = DBUtils.queryListThrowsException(sql, null, TCashLog.class);
		for (TCashLog tcashLog : cusLt) {
			hm.put(tcashLog.user_id, tcashLog.create_time);
		}

		return hm;
	}

	private HashMap<String, BigDecimal> getAllUsersSumCashInOut(String date) throws Exception {
		HashMap<String, BigDecimal> hm = new HashMap<String, BigDecimal>();
		String sql = String.format(
				"SELECT user_id,sum(amount) amount,side FROM dc_cash_log where  status='1' and cash_type='1'  group by user_id,side");
		if (StringUtils.isNoneBlank(date)) {
			sql = String.format(
					"SELECT user_id,sum(amount) amount,side FROM dc_cash_log  where left(update_time,10)='%s' and status='1' and cash_type='1' group by user_id,side",
					date);
		}
		List<TCashLog> cusLt = DBUtils.queryListThrowsException(sql, null, TCashLog.class);
		for (TCashLog tcashLog : cusLt) {
			hm.put(tcashLog.user_id + "_" + tcashLog.side, tcashLog.amount);
		}
		log.info("getAllUsersSumCashInOut date:{},size:{}", date, cusLt.size());

		return hm;
	}

	private HashMap<String, TOrdersExecorders> getAllUsersSumExecOrder(String date) throws Exception {
		HashMap<String, TOrdersExecorders> hm = new HashMap<String, TOrdersExecorders>();
		String sql = String.format(
				"SELECT user_id,exec_type,sum(exec_value) exec_value,sum(closed_pnl) closed_pnl,sum(exec_fee) exec_fee  FROM dc_orders_execorders group by user_id,exec_type");
		if (StringUtils.isNoneBlank(date)) {
			sql = String.format(
					"SELECT user_id,exec_type,sum(exec_value) exec_value,sum(closed_pnl) closed_pnl,sum(exec_fee) exec_fee  FROM dc_orders_execorders where left(create_time,10)='%s' group by user_id,exec_type",
					date);
		}
		List<TOrdersExecorders> cusLt = DBUtils.queryListThrowsException(sql, null, TOrdersExecorders.class);
		for (TOrdersExecorders tOrdersExecorders : cusLt) {
			hm.put(tOrdersExecorders.user_id + "_" + tOrdersExecorders.exec_type, tOrdersExecorders);
		}
		log.info("getAllUsersSumExecOrder date:{},size:{}", date, cusLt.size());

		return hm;
	}

	private HashMap<String, TOrdersExecorders> getAllUsersSumExecFeeOrder(String date) throws Exception {
		HashMap<String, TOrdersExecorders> hm = new HashMap<String, TOrdersExecorders>();
		String sql = String.format(
				"SELECT user_id,sum(exec_fee) exec_fee FROM dc_orders_execorders where left(create_time,10)='%s' and exec_fee>0 group by user_id",
				date);

		List<TOrdersExecorders> cusLt = DBUtils.queryListThrowsException(sql, null, TOrdersExecorders.class);
		for (TOrdersExecorders tOrdersExecorders : cusLt) {
			hm.put(tOrdersExecorders.user_id, tOrdersExecorders);
		}
		log.info("getAllUsersSumExecFeeOrder date:{},size:{}", date, cusLt.size());

		return hm;
	}


	private HashSet<String> getUserIdNumberCashInMark() throws Exception {
		HashSet<String> cashInMark = new HashSet<>();
		String sql = "SELECT user_id FROM dc_kol_rpt_day where invite_number_cash_in >0 group by user_id";
		List<TKolRptDay> cusLt = DBUtils.queryListThrowsException(sql, null, TKolRptDay.class);
		for (TKolRptDay kolRptDay : cusLt) {
			cashInMark.add(kolRptDay.user_id);
		}
		log.info("getUserIdNumberCashInMark size:{}, cashInMark set size:{}", cusLt.size(), cashInMark.size());

		return cashInMark;
	}


	private HashSet<String> getUserIdNumberTradeMark() throws Exception {
		HashSet<String> tradeMark = new HashSet<>();
		String sql = "SELECT user_id FROM dc_kol_rpt_day where invite_number_trade >0 group by user_id";
		List<TKolRptDay> cusLt = DBUtils.queryListThrowsException(sql, null, TKolRptDay.class);
		for (TKolRptDay kolRptDay : cusLt) {
			tradeMark.add(kolRptDay.user_id);
		}
		log.info("getUserIdNumberCashInMark size:{}, tradeMark set size:{}", cusLt.size(), tradeMark.size());

		return tradeMark;
	}


	public static String getDate(String dateStr,String type) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String date;
		if (type.equals(Consts.Kol_Batch_History)) {
			format.parse(dateStr);
			date = dateStr;
		}else{
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DAY_OF_YEAR, -1);
			date = format.format(cal.getTime());
		}
		return date;
	}
	public static String getLastDate(String dateStr,String type) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String date;
		if (type.equals(Consts.Kol_Batch_History)) {
			Calendar cal = Calendar.getInstance();
			Date startDate = format.parse(dateStr);
			cal.setTime(startDate);
			cal.add(Calendar.DAY_OF_YEAR, -1);
			date = format.format(cal.getTime());
		}else {
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DAY_OF_YEAR, -2);
			date = format.format(cal.getTime());
		}
		return date;
	}

	public static String getMonth(String dateStr) throws ParseException {
		SimpleDateFormat monthFormat = new SimpleDateFormat("yyyy-MM");
		String date;
		if (StringUtils.isNoneBlank(dateStr)) {
			SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
			Date startDate = format1.parse(dateStr);
			date = monthFormat.format(startDate);
		} else {
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DAY_OF_YEAR, -1);
			date = monthFormat.format(cal.getTime());
		}
		return date;
	}

	public static String getDateTime() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = format.format(new Date());
		return date;
	}

	public static int getCount(String sql) throws Exception {

		Object o = DBUtils.querySimpleObject(sql, null, int.class);

		return ((Integer) o).intValue();
	}

	private HashMap<String, TKolPartner> getAllTKolPartner(String date,String type) throws Exception {
		HashMap<String, TKolPartner> hm = new HashMap<String, TKolPartner>();

		String selectAllUser = "select * from dc_kol_partner where status='1'";
		if (type.equals(Consts.Kol_Batch_History)){
			selectAllUser = String.format("select * from dc_kol_partner where status='1' and left(create_time,10)<='%s'",date);
		}
		List<TKolPartner> users = DBUtils.queryListThrowsException(selectAllUser, new Object[] {}, TKolPartner.class);
		for (TKolPartner tKolPartner : users) {
			hm.put(tKolPartner.user_id, tKolPartner);
		}
		log.info("getAllTKolPartner size:{}", users.size());
		return hm;
	}



	private HashMap<String, TKolRpt> getTKolRpt() throws Exception {
		HashMap<String, TKolRpt> hm = new HashMap<String, TKolRpt>();

		String selectAllUser = "select * from dc_kol_rpt";
		List<TKolRpt> users = DBUtils.queryListThrowsException(selectAllUser, new Object[] {}, TKolRpt.class);
		for (TKolRpt tKolPartner : users) {
			hm.put(tKolPartner.kol_user_id, tKolPartner);
		}
		log.info("getTKolRpt size:{}", users.size());
		return hm;
	}

	private List<TUsers> getAllUsers(String date,String type) throws Exception {
		String selectAllUser = "select * from dc_users where user_type='1'";
		if (type.equals(Consts.Kol_Batch_History)) {
			selectAllUser = String.format( "select * from dc_users where user_type='1' and left(create_time,10)<='%s'",date);
		}

		List<TUsers> users = DBUtils.queryListThrowsException(selectAllUser, new Object[] {}, TUsers.class);
		log.info("getAllUsers size:{}", users.size());
		return users;
	}

	private HashMap<String, TUsersRpt> getAllUsersRpt(String date) throws Exception {
		HashMap<String, TUsersRpt> hm = new HashMap<String, TUsersRpt>();
		String selectCus =String.format( "select * from dc_users_rpt_day where  trade_date='%s'",date);
		List<TUsersRpt> cusLt = DBUtils.queryListThrowsException(selectCus, null, TUsersRpt.class);
		for (TUsersRpt tKolCusRetSerCount : cusLt) {
			hm.put(tKolCusRetSerCount.user_id, tKolCusRetSerCount);
		}
		log.info("getAllUsersRpt:{},size:{}", date,cusLt.size());

		return hm;
	}


	private HashMap<String, TUsersBalance> getUsersBalance(String date,String type) throws Exception {
		HashMap<String, TUsersBalance> hm = new HashMap();
		String selectCus="select * from dc_users_balance";
		if (type.equals(Consts.Kol_Batch_History)){
			selectCus = String.format( "select * from dc_users_balance_day where trade_date='%s'",date);
		}
		List<TUsersBalance> cusLt = DBUtils.queryListThrowsException(selectCus, null, TUsersBalance.class);
		for (TUsersBalance tKolCusRetSerCount : cusLt) {
			hm.put(tKolCusRetSerCount.user_id+"", tKolCusRetSerCount);
		}
		log.info("getUsersBalance date:{},size:{}", date,cusLt.size());

		return hm;
	}


	private List<TOrdersPosition> getTOrdersPosition(String date,String type) throws Exception {
		List<TOrdersPosition> hm = new ArrayList<>();
		String sql = "select * from dc_orders_position where size > 0  limit %s,%s";
		String hSqlParam ="";
		if (type.equals(Consts.Kol_Batch_History)){
			sql = "select * from dc_orders_position_day where size > 0 and trade_date='"+date+"' limit %s,%s";
		}

		//String countSql = String.format("select count(*) from %s where size >0",tableName);
		int pageSize = 2000;
		int pageNum = 0;
		while (true) {
			String selectCus = String.format(sql, pageNum * pageSize, pageSize);
			List<TOrdersPosition> cusLt = DBUtils.queryListThrowsException(selectCus, null, TOrdersPosition.class);
			for (TOrdersPosition tOrdersPosition : cusLt) {
				if (tOrdersPosition.size.compareTo(BigDecimal.ZERO) !=0) {
					hm.add(tOrdersPosition);
				}
			}
			log.info("while TOrdersPosition date:{},size:{}", date,cusLt.size());
			if(cusLt == null){
				break;
			}
			if (cusLt.size() < pageSize){
				break;
			}
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
			}
		}
//		if (type.equals(Consts.Kol_Batch_History)){
//			selectCus = String.format( "select * from dc_users_balance_day where trade_date='%s'",date);
//		}

		log.info("getTOrdersPosition:{},size:{}", date,hm.size());

		return hm;
	}


	private HashMap<String, TMarkPriceDay> getTMarkPriceDay(String date) throws Exception {
		HashMap<String, TMarkPriceDay> hm = new HashMap();
		int pageSize = 2000;
		int pageNum = 0;

		while (true) {
			String selectCus = String.format("select * from dc_mark_price_day where trade_date='%s' limit %s,%s",date, pageNum * pageSize, pageSize);
			List<TMarkPriceDay> cusLt = DBUtils.queryListThrowsException(selectCus, null, TMarkPriceDay.class);
			for (TMarkPriceDay tMarkPriceDay : cusLt) {
				hm.put(tMarkPriceDay.symbol, tMarkPriceDay);
			}
			log.info("while TMarkPriceDay date:{},size:{}", date,cusLt.size());
			if(cusLt == null){
				break;
			}
			if (cusLt.size() < pageSize){
				break;
			}
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
			}
		}
//		if (type.equals(Consts.Kol_Batch_History)){
//			selectCus = String.format( "select * from dc_users_balance_day where trade_date='%s'",date);
//		}

		log.info("getTMarkPriceDay:{},size:{}", date,hm.size());

		return hm;
	}


	private void insertKolRptDay(List<TKolRptDay> lt) throws Exception {
		for (TKolRptDay tUsersRptDay : lt) {
			tUsersRptDay.update_time = tUsersRptDay.create_time = getDateTime();
		}
		DBUtils.insertList(lt, "dc_kol_rpt_day");
		log.info("insertKolRptDay size:{}", lt.size());

	}


	private void deleteTKolRptDetail(String date) throws Exception {

		DBUtils.update(String.format("delete from dc_kol_rpt_detail where trade_date='%s'", date), new Object[] {});
		log.info("deleteTKolRptDetail :{} success", date);

	}

	private void deleteTKolRptDay(String date) throws Exception {

		DBUtils.update(String.format("delete from dc_kol_rpt_day where trade_date='%s'", date), new Object[] {});
		log.info("deleteTKolRptDay :{} success", date);

	}

	private void insertTKolRptDetail(List<TKolRptDetail> lt) throws Exception {
		log.info("insertTKolRptDetail size:{} begin", lt.size());
		DBUtils.insertList(lt, "dc_kol_rpt_detail");
		log.info("insertTKolRptDetail size:{} end", lt.size());

	}

	private void deleteTKolURLRptDay(String date) throws Exception {
		DBUtils.update(String.format("delete from dc_kol_url_rpt_day where trade_date='%s'", date), new Object[] {});
		log.info("deleteTKolURLRptDay :{} success", date);

	}

	private void insertTKolURLRpt(List<TKolUrlRptDay> lt) throws Exception {
		log.info("insertTKolURLRpt size:{} begin", lt.size());
		DBUtils.insertList(lt, "dc_kol_url_rpt_day");
		log.info("insertTKolURLRpt size:{} end", lt.size());

	}

	private HashMap<String, TKolRptMonth> getKolRptMonth(String month) throws Exception {
		HashMap<String, TKolRptMonth> hm = new HashMap<String, TKolRptMonth>();
		String sql = String.format("select * from dc_kol_rpt_month where month='%s'", month);
		List<TKolRptMonth> list = DBUtils.queryListThrowsException(sql, new Object[] {}, TKolRptMonth.class);
		for (TKolRptMonth tKolRptMonth : list) {
			hm.put(tKolRptMonth.user_id, tKolRptMonth);
		}
		log.info("getKolRptMonth size:{}", hm.size());
		return hm;
	}

	private HashMap<String, TKolRuleDetail> getKolRules() throws Exception {
		HashMap<String, TKolRuleDetail> hm = new HashMap<String, TKolRuleDetail>();
		String sql = "select * from dc_kol_rule_detail order by id asc";
		List<TKolRuleDetail> list = DBUtils.queryListThrowsException(sql, new Object[] {}, TKolRuleDetail.class);
		for (TKolRuleDetail tKolRptMonth : list) {
			hm.put(tKolRptMonth.grade_name, tKolRptMonth);
		}
		log.info("getKolRules size:{}", hm.size());
		return hm;
	}


	private List<TKolRptMonth> getKolRptMonthByKolRptDay(String date) throws Exception {
		String month = getMonth(date);
		String time = getDateTime();
		String selectCus = String.format(
				"SELECT kol_user_id user_id,sum(acc_transact_vol) total_inv_tran_vol_mon,sum(invite_number) total_inv_tran_num_mon FROM dc_kol_rpt_day where left(trade_date,7)='%s' group by kol_user_id",
				month);
		List<TKolRptMonth> cusLt = DBUtils.queryListThrowsException(selectCus, null, TKolRptMonth.class);
		for (TKolRptMonth tKolRptMonth : cusLt) {
			tKolRptMonth.month = month;
			tKolRptMonth.create_time = tKolRptMonth.update_time = time;
			tKolRptMonth.close_by = Consts.ServerName;
		}
		log.info("getKolRptMonthByKolRptDay size:{}", cusLt.size());

		return cusLt;
	}

	private void insertTKolRptMonth(List<TKolRptMonth> lt) throws Exception {
		List<Object[]> ltO = new ArrayList<Object[]>();
		for (TKolRptMonth kolRptMonth : lt) {

			ltO.add(new Object[] { kolRptMonth.user_id, kolRptMonth.month, kolRptMonth.total_inv_tran_num_mon,
					kolRptMonth.total_inv_tran_vol_mon, kolRptMonth.create_time, kolRptMonth.update_time,
					kolRptMonth.close_by });
		}
		DBUtils.updateList(
				"replace into dc_kol_rpt_month(user_id,month,total_inv_tran_num_mon,total_inv_tran_vol_mon,create_time,update_time,close_by) value(?,?,?,?,?,?,?)",
				ltO);
		log.info("insertTKolRptMonth size:{} end", lt.size());

	}

	private List<TKolRpt> getKolRptTotal() throws Exception {
		String time = getDateTime();
		HashMap<String, TKolRpt> hmKolRpt = getTKolRpt();
		List<TKolRpt> lt = new ArrayList<TKolRpt>();
		String selectCus = String.format(
				"select kol_user_id,type,sum(acc_deposit_amount) acc_deposit_amount,sum(acc_withdraw_amount) acc_withdraw_amount,sum(acc_transact_vol) acc_transact_vol, sum(acc_transact_pl) acc_transact_pl,sum(acc_transact_fee) acc_transact_fee,sum(taker_volume) taker_volume,sum(maker_volume) maker_volume, sum(acc_com_reb) acc_com_reb,sum(invite_number) invite_number,sum(invite_number_cash_in) invite_number_cash_in,sum(invite_number_trade) invite_number_trade from dc_kol_rpt_day group by kol_user_id,type");
		List<TKolRptDay> cusLt = DBUtils.queryListThrowsException(selectCus, null, TKolRptDay.class);

//		HashMap<String, TKolRpt> hmKolRpt = new HashMap<String, TKolRpt>();
		for (TKolRptDay kolRptDay : cusLt) {
			String user_id = kolRptDay.kol_user_id;
			TKolRpt kolRpt = hmKolRpt.get(user_id);
			if (kolRpt != null) {
				kolRpt.acc_com_reb = new BigDecimal(0.00000000);
			}
		}
		for (TKolRptDay kolRptDay : cusLt) {
			String type = kolRptDay.type;
			String user_id = kolRptDay.kol_user_id;
			TKolRpt kolRpt = hmKolRpt.get(user_id);
			if (kolRpt == null) {
				kolRpt = new TKolRpt();
				kolRpt.kol_user_id = user_id;
				kolRpt.create_time = time;
				hmKolRpt.put(user_id, kolRpt);
			}
			if ("1".equals(type)) {

				kolRpt.acc_deposit_amount1 = kolRptDay.acc_deposit_amount;
				kolRpt.acc_withdraw_amount1 = kolRptDay.acc_withdraw_amount;
				kolRpt.acc_transact_vol1 = kolRptDay.acc_transact_vol;
				kolRpt.acc_transact_pl1 = kolRptDay.acc_transact_pl;
				kolRpt.acc_transact_fee1 = kolRptDay.acc_transact_fee;
				kolRpt.taker_volume1 = kolRptDay.taker_volume;
				kolRpt.maker_volume1 = kolRptDay.maker_volume;
				kolRpt.acc_com_reb1 = kolRptDay.acc_com_reb;
				kolRpt.invite_number1 = kolRptDay.invite_number;
				kolRpt.invite_number_trade1 = kolRptDay.invite_number_trade;
				kolRpt.invite_number_cash_in1 = kolRptDay.invite_number_cash_in;

				if (kolRpt.acc_com_reb == null) {
					kolRpt.acc_com_reb = kolRpt.acc_com_reb1;
				} else {
					kolRpt.acc_com_reb = kolRpt.acc_com_reb.add(kolRpt.acc_com_reb1);
				}
			} else if ("2".equals(type)) {

				kolRpt.acc_deposit_amount2 = kolRptDay.acc_deposit_amount;
				kolRpt.acc_withdraw_amount2 = kolRptDay.acc_withdraw_amount;
				kolRpt.acc_transact_vol2 = kolRptDay.acc_transact_vol;
				kolRpt.acc_transact_pl2 = kolRptDay.acc_transact_pl;
				kolRpt.acc_transact_fee2 = kolRptDay.acc_transact_fee;
				kolRpt.taker_volume2 = kolRptDay.taker_volume;
				kolRpt.maker_volume2 = kolRptDay.maker_volume;
				kolRpt.acc_com_reb2 = kolRptDay.acc_com_reb;
				kolRpt.invite_number2 = kolRptDay.invite_number;
				kolRpt.invite_number_trade2 = kolRptDay.invite_number_trade;
				kolRpt.invite_number_cash_in2 = kolRptDay.invite_number_cash_in;

				if (kolRpt.acc_com_reb == null) {
					kolRpt.acc_com_reb = kolRpt.acc_com_reb2;
				} else {
					kolRpt.acc_com_reb = kolRpt.acc_com_reb.add(kolRpt.acc_com_reb2);
				}
			}
			kolRpt.update_time = time;

		}
		lt.addAll(hmKolRpt.values());
		log.info("getKolRptTotal size:{}", lt.size());

		return lt;
	}


	private void deleteTKolRpt() throws Exception {
		DBUtils.update(String.format("delete from dc_kol_rpt"), new Object[] {});
		log.info("deleteTKolRpt success");

	}


	private void insertTKolRpt(List<TKolRpt> lt) throws Exception {
		DBUtils.insertList(lt, "dc_kol_rpt");
		log.info("insertTKolRpt size:{}", lt.size());
	}


	private List<TKolUrlRptDay> getKolUrlKolRpt(String date,HashMap<String, TKolPartner> hmpartners) throws Exception {

		String sql = String.format(
				"select kol_user_id, sum(acc_deposit_amount) as acc_deposit_amount, sum(acc_withdraw_amount) as acc_withdraw_amount, sum(acc_transact_vol) as acc_transact_vol,sum(acc_transact_pl) as acc_transact_pl, sum(acc_transact_fee) as acc_transact_fee,sum(taker_volume) as taker_volume,sum(maker_volume) as maker_volume,sum(acc_com_reb) as acc_com_reb, sum(invite_number) as invite_number, sum(invite_number_cash_in) as invite_number_cash_in,sum(invite_number_trade) as invite_number_trade, url from dc_kol_rpt_day where trade_date='%s'  group by kol_user_id, url",
				date);
		List<TKolUrlRptDay> lt = DBUtils.queryListThrowsException(sql, null, TKolUrlRptDay.class);
		log.info("getKolUrlKolRpt size:{}", lt.size());
		return lt;
	}

	public void monUrlKolJob(String date, HashMap<String, TKolPartner> hmpartners) throws Exception {
		List<TKolUrlRptDay> lt = getKolUrlKolRpt(date,hmpartners);
		deleteTKolURLRptDay(date);
		String time = getDateTime();
		if (lt != null) {
			for (TKolUrlRptDay kolUrlRptDay : lt) {
				if (hmpartners.containsKey(kolUrlRptDay.kol_user_id)) {
					TKolPartner po = hmpartners.get(kolUrlRptDay.kol_user_id);
					kolUrlRptDay.kol_time = po.kol_time;
					kolUrlRptDay.id = IdUtil.getId();
					kolUrlRptDay.trade_date = date;
				}
				kolUrlRptDay.create_time = kolUrlRptDay.update_time = time;
			}
		}
		insertTKolURLRpt(lt);

	}

	public void monKolJob(String date) throws Exception {
		List<TKolRptMonth> lt = getKolRptMonthByKolRptDay(date);
		insertTKolRptMonth(lt);
	}
}
