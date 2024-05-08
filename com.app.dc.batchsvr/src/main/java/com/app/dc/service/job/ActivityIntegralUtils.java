package com.app.dc.service.job;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.app.common.db.DBUtils;
import com.app.common.utils.StringUtil;
import com.app.dc.entity.*;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
public class ActivityIntegralUtils {


    public static boolean checkActivityIntegralDatel(TUsers user, List<AirdropActivityDate> airdropActivityDates){
        boolean status = false;
        if (airdropActivityDates != null) {
            for (AirdropActivityDate airdropActivityDate : airdropActivityDates) {

                Date startDate = null;
                Date endDate = null;
                Date create_time = null;
                if (airdropActivityDate.activity == 1) {
                    try {
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        startDate = format.parse(airdropActivityDate.startDate + " 00:00:00");
                        if (airdropActivityDate.endDate != null && StringUtil.isNotEmpty(airdropActivityDate.endDate)) {
                            endDate = format.parse(airdropActivityDate.endDate + " 23:59:59");
                        }
                    } catch (ParseException e) {
                        log.error("SimpleDateFormat ParseException:{}", e);
                        return false;
                    }
                    try {
                        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        create_time = format1.parse(user.create_time);
                    } catch (ParseException e) {
                        log.error("SimpleDateFormat ParseException:{}", e);
                        return false;
                    }
                    if (endDate == null) {
                        if (startDate.getTime() <= create_time.getTime()) {
                            if (airdropActivityDate.activity == 1) {
                                status = true;
                            }
                        }
                    } else if (startDate.getTime() <= create_time.getTime() && endDate.getTime() >= create_time.getTime()) {
                        if (airdropActivityDate.activity == 1) {
                            status = true;
                        }

                    }
                }
            }
        }
        return status;
    }


    public static boolean isActivity1Present(List<AirdropActivityDate> airdropActivityDates){
        boolean status = false;
        if (airdropActivityDates != null) {
            for (AirdropActivityDate airdropActivityDate : airdropActivityDates) {
                if (airdropActivityDate.activity == 1) {
                    status = true;
                }
            }
        }
        return status;
    }




    public static boolean checkActivityIntegralDate(String  date, int activity, List<AirdropActivityDate> airdropActivityDates){
        boolean status = false;
        if (airdropActivityDates != null) {
            for (AirdropActivityDate airdropActivityDate : airdropActivityDates) {

                Date startDate = null;
                Date endDate = null;
                Date create_time = null;
                if (airdropActivityDate.activity == activity) {
                    try {
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        startDate = format.parse(airdropActivityDate.startDate + " 00:00:00");
                        if (airdropActivityDate.endDate != null && StringUtil.isNotEmpty(airdropActivityDate.endDate)) {
                            endDate = format.parse(airdropActivityDate.endDate + " 23:59:59");
                        }
                    } catch (ParseException e) {
                        log.error("SimpleDateFormat ParseException:{}", e);
                        return false;
                    }
                    try {
                        if (date.length() == 10) {
                            SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
                            create_time = format1.parse(date);
                        } else if (date.length() == 19) {
                            SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            create_time = format1.parse(date);
                        }
                    } catch (ParseException e) {
                        log.error("SimpleDateFormat ParseException:{}", e);
                        return false;
                    }
                    if (endDate == null) {
                        if (startDate.getTime() <= create_time.getTime()) {
                            if (airdropActivityDate.activity == activity) {
                                status = true;
                            }
                        }
                    } else if (startDate.getTime() <= create_time.getTime() && endDate.getTime() >= create_time.getTime()) {
                        if (airdropActivityDate.activity == activity) {
                            status = true;
                        }

                    }
                }
            }
        }
        return status;
    }

    public static void checkTotalAssets(BigDecimal total_assets, JSONObject jsonObject, TActivity2TaskCount tActivity2TaskCount, TActivity2TaskPoints tActivity2TaskPoints){
        if (total_assets.compareTo(BigDecimal.ZERO) > 0) {
            JSONArray weekbalances = jsonObject.getJSONArray("weekbalance");
            List<JSONObject> objectList = new ArrayList<>();
            for (Object o : weekbalances) {
                JSONObject jsonObject1 = (JSONObject) o;
                int level = jsonObject1.getIntValue("level");
                objectList.add(jsonObject1);
            }
            objectList = ReverseOrder(objectList);
            for (JSONObject jsonObject1 : objectList) {
                BigDecimal balance = jsonObject1.getBigDecimal("balance");
                int integral = jsonObject1.getIntValue("integral");
                int level = jsonObject1.getIntValue("level");
                if (total_assets.compareTo(balance) >= 0) {
                    tActivity2TaskCount.week_balance_count = tActivity2TaskCount.week_balance_count + 1;
                    break;
                } else {
                    if (tActivity2TaskPoints.week_balance.compareTo(balance) >= 0) {
                        tActivity2TaskCount.week_balance_count = 0;
                        break;
                    }
                }
            }

            JSONArray accounttotalbalances = jsonObject.getJSONArray("accounttotalbalance");
            for (Object o : accounttotalbalances) {
                JSONObject jsonObject1 = (JSONObject) o;
                BigDecimal balance = jsonObject1.getBigDecimal("balance");
                int integral = jsonObject1.getIntValue("integral");
                int level = jsonObject1.getIntValue("level");
                if (level == 1) {
                    if (total_assets.compareTo(balance) >= 0) {
                        tActivity2TaskCount.account_balance_count1 = tActivity2TaskCount.account_balance_count1 + 1;
                    } else {
                        tActivity2TaskCount.account_balance_count1 = 0;
                    }
                } else if (level == 2) {
                    if (total_assets.compareTo(balance) >= 0) {
                        tActivity2TaskCount.account_balance_count2 = tActivity2TaskCount.account_balance_count2 + 1;
                    } else {
                        tActivity2TaskCount.account_balance_count2 = 0;
                    }
                }
            }
        }
    }


    public static List<JSONObject> ReverseOrder(List<JSONObject> list){
        Collections.sort(list, new Comparator<JSONObject>() {
            @Override
            public int compare(JSONObject person1, JSONObject person2) {
                return Integer.compare(person2.getIntValue("level"), person1.getIntValue("level"));
            }
        });
        return list;
    }


    public static void updateActivity(List<TUsersRpt> tUsersRpts,String date) throws Exception {
        String sql = "UPDATE dc_users_rpt set  integral=activity2_integral+activity1_integral where user_id=?";
        List<Object[]> args = new ArrayList<>();
        for (TUsersRpt tUsersRpt : tUsersRpts){
            Object[] o = new Object[1];
            o[0]= tUsersRpt.user_id;
            args.add(o);
        }
        String sql1 = "UPDATE dc_users_rpt_day set integral=activity2_integral+activity1_integral where user_id=? and trade_date=?";
        List<Object[]> args1 = new ArrayList<>();
        for (TUsersRpt tUsersRpt : tUsersRpts){
            Object[] o = new Object[2];
            o[0]= tUsersRpt.user_id;
            o[1]= date;
            args1.add(o);
        }
        DBUtils.updateList(sql,args);
        DBUtils.updateList(sql1,args1);
    }


    public static void updateActivityIntegral(List<TUsersRpt> tUsersRpts,HashMap<String, TUsersRptDay> hmTUsersRptDay) throws Exception {
        for (TUsersRpt rpt: tUsersRpts){
            rpt.integral = rpt.activity1_integral + rpt.activity2_integral;
            if (hmTUsersRptDay.containsKey(rpt.user_id)){
                TUsersRptDay tUsersRptDay = hmTUsersRptDay.get(rpt.user_id);
                tUsersRptDay.integral = rpt.integral;
            }
        }
    }



    public static void checkTotalAssets(String date,String markPriceDate)throws Exception {
        log.info("checkTotalAssets date:{} markPriceDate:{}",date,markPriceDate);
        List<AirdropActivityDate> airdropActivityDates = JobDao.getAirdropActivityDate();
        boolean ctivityIntegralDate2Status = ActivityIntegralUtils.checkActivityIntegralDate(date,2, airdropActivityDates);
        if (ctivityIntegralDate2Status) {
            HashMap<String, TUsersBalance> hmUserBalance = getUsersBalance();
            JSONObject jsonObject = JobDao.getAirdrop2Param();
            HashMap<String, TMarkPriceDay> hmTMarkPriceDay = JobDao.getTMarkPriceDay(markPriceDate);
            List<TOrdersPosition> tOrdersPositions = getTOrdersPosition();
            HashMap<String, BigDecimal> unsettled_plMap = new HashMap<>();
            calUnsettled_pl(tOrdersPositions, hmTMarkPriceDay, unsettled_plMap);
            HashMap<String, TActivity2TaskCount> hmTaskCount = JobDao.getTActivity2TaskCount();
            HashMap<String, TActivity2TaskPoints> hmTaskPoints = JobDao.getActivity2TaskPoints();
            List<TActivity2TaskPoints> tActivity2TaskPointsList = new ArrayList<>();
            List<TActivity2TaskCount> tActivity2TaskCounts = new ArrayList<>();
            for (TUsersBalance tUsersBalance : hmUserBalance.values()) {
                TActivity2TaskPoints tActivity2TaskPoints = hmTaskPoints.get(tUsersBalance.user_id + "");
                TActivity2TaskCount tActivity2TaskCount = hmTaskCount.get(tUsersBalance.user_id + "");
                if (tActivity2TaskPoints == null) {
                    tActivity2TaskPoints = new TActivity2TaskPoints();
                    tActivity2TaskPoints.user_id = tUsersBalance.user_id + "";
                }
                if (tActivity2TaskCount == null) {
                    tActivity2TaskCount = new TActivity2TaskCount();
                    tActivity2TaskCount.user_id = tUsersBalance.user_id + "";
                }
                tActivity2TaskPointsList.add(tActivity2TaskPoints);
                tActivity2TaskCounts.add(tActivity2TaskCount);
                BigDecimal lock_balance = tUsersBalance.lock_balance == null ? BigDecimal.ZERO : tUsersBalance.lock_balance;
                BigDecimal account_balance = tUsersBalance.account_balance == null ? BigDecimal.ZERO : tUsersBalance.account_balance;
                BigDecimal unsettled_pl = unsettled_plMap.get(tUsersBalance.user_id);
                if (unsettled_pl == null) {
                    unsettled_pl = BigDecimal.ZERO;
                }
                BigDecimal total_assets = account_balance.add(unsettled_pl).add(lock_balance);
                if (total_assets.compareTo(BigDecimal.ZERO) > 0) {
                    ActivityIntegralUtils.checkTotalAssets(total_assets,jsonObject,tActivity2TaskCount,tActivity2TaskPoints);
                }else {
                    if (tActivity2TaskCount.week_balance_count >0){
                        tActivity2TaskCount.week_balance_count = 0;
                    }
                    if (tActivity2TaskCount.account_balance_count1 >0){
                        tActivity2TaskCount.account_balance_count1 = 0;
                    }
                    if (tActivity2TaskCount.account_balance_count2 >0){
                        tActivity2TaskCount.account_balance_count2 = 0;
                    }
                }
            }
            JobDao.deleteTActivity2TaskCount();
            JobDao.insertTActivity2TaskCount(tActivity2TaskCounts);
        }
    }

    private static List<TOrdersPosition> getTOrdersPosition() throws Exception {
        List<TOrdersPosition> hm = new ArrayList<>();
        String sql = "select * from dc_orders_position where size > 0  limit %s,%s";

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
            log.info("while TOrdersPosition size:{}",cusLt.size());
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

        log.info("getTOrdersPosition size:{}",hm.size());

        return hm;
    }


    private static void calUnsettled_pl(List<TOrdersPosition> tOrdersPositions, HashMap<String, TMarkPriceDay> hmTMarkPriceDay, HashMap<String, BigDecimal> plMap){

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

    private static HashMap<String, TUsersBalance> getUsersBalance() throws Exception {
        HashMap<String, TUsersBalance> hm = new HashMap();
        String selectCus="select * from dc_users_balance";
        List<TUsersBalance> cusLt = DBUtils.queryListThrowsException(selectCus, null, TUsersBalance.class);
        for (TUsersBalance tKolCusRetSerCount : cusLt) {
            hm.put(tKolCusRetSerCount.user_id+"", tKolCusRetSerCount);
        }
        log.info("getUsersBalance size:{}", cusLt.size());

        return hm;
    }
}
