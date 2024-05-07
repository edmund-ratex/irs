package com.app.dc.service.job;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.app.common.db.DBUtils;
import com.app.common.utils.StringUtil;
import com.app.dc.entity.*;
import com.app.dc.util.Consts;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
public class Activity2Job implements Job {

    private static boolean runFlag = false;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("Activity2Job execute");
        Job();
    }

    public synchronized void Job() {
        if (!runFlag) {
            runFlag = true;
            try {
                updateActivity2();
                log.info("Activity2Job Job success");
            } catch (Exception e) {
                log.error("updateActivity2 error:{}", e+e.getMessage());
            }finally {
                runFlag = false;
            }
        } else {
            log.warn("Activity2Job job is runing.");

        }
    }

    public void updateActivity2()throws Exception {
        List<AirdropActivityDate> airdropActivityDates = JobDao.getAirdropActivityDate();
        String date = getDate();
        String lastDate = getLastDate();
//        String date = "2023-08-05";
//        String lastDate = "2023-08-04";
        log.info("Activity2Job date:{} lastDate:{}",date,lastDate);
        boolean activityIntegralDate2Status = ActivityIntegralUtils.checkActivityIntegralDate(date,2, airdropActivityDates);
        List<String> specialTasksChains = new ArrayList<>();
        if (activityIntegralDate2Status) {
            JSONObject jsonObject = JobDao.getAirdrop2Param();
            JSONArray specialTasksChain = jsonObject.getJSONArray("specialTasksChain");
            for (Object o : specialTasksChain) {
                JSONObject jsonObject1 = (JSONObject) o;
                String id = jsonObject1.getString("id");
                Date startDate = null;
                Date endDate = null;
                Date create_time = null;
                try {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String startDateStr = jsonObject1.getString("startDate");
                    String endDateStr = jsonObject1.getString("endDate");
                    startDate = format.parse(startDateStr + " 00:00:00");
                    if (endDateStr != null && StringUtil.isNotEmpty(endDateStr)) {
                        endDate = format.parse(endDateStr + " 23:59:59");
                    }
                } catch (ParseException e) {
                    log.error("SimpleDateFormat ParseException:{}", e);
                }

                create_time = new Date();
                if (endDate == null) {
                    if (startDate.getTime() <= create_time.getTime()) {
                        specialTasksChains.add(id);
                    }
                } else if (startDate.getTime() <= create_time.getTime() && endDate.getTime() >= create_time.getTime()) {
                    specialTasksChains.add(id);
                }
            }
            String chainIds = null;
            if (specialTasksChains.size() > 0){
                for (String  s : specialTasksChains) {
                    if (chainIds == null) {
                        chainIds = String.format("'%s'",s);
                    } else {
                        chainIds = String.format("%s,'%s'",chainIds,s);
                    }
                }
            }
            log.info("select chainId:{}", chainIds);
            HashMap<String, TUsersRpt> hmUsersRpt = getUsersRpt();
            HashMap<String, TUsersRptDay> hmUsersRptDay = getAllUsersRptDay(date);
            HashMap<String, TNftOwners> hmNftOwners = getTNftOwners();
            HashMap<String, BigDecimal> hmUserCashInOutDate = JobDao.getAllUsersSumCashInOut(date);
            HashMap<String, BigDecimal> hmUserChainIdCashInDate = JobDao.getAllUsersSumCashInByChainId(date,chainIds);
            HashMap<String, BigDecimal> hmUserExecOrderDate = getAllUsersSumExecOrder(date);
            HashMap<String, TActivity2TaskPoints> hmTaskPoints = JobDao.getLastActivity2TaskPoints(lastDate);
            List<TUsers> users = getAllUsers();
            List<TActivity2TaskPoints> tActivity2TaskPointsList = new ArrayList<>();
            HashMap<String, TUsers> hmReferrer = new HashMap<>();
            HashMap<String, TActivity2TaskPoints> hmNewTaskPoints = new HashMap<>();
            HashMap<String, TActivity2TaskCount> hmTaskCount = JobDao.getTActivity2TaskCount();
            for (TUsers tUsers : users){
                hmReferrer.put(tUsers.user_id, tUsers);
                TActivity2TaskPoints tActivity2TaskPoints = hmTaskPoints.get(tUsers.user_id);
                if (tActivity2TaskPoints == null){
                    tActivity2TaskPoints = new TActivity2TaskPoints();
                    tActivity2TaskPoints.user_id = tUsers.user_id;
                }
                TActivity2TaskCount tActivity2TaskCount = hmTaskCount.get(tUsers.user_id);
                if (tActivity2TaskCount == null) {
                    tActivity2TaskCount = new TActivity2TaskCount();
                    tActivity2TaskCount.user_id = tUsers.user_id;
                    tActivity2TaskCount.account_balance_count1 = 0;
                    tActivity2TaskCount.account_balance_count2 = 0;
                    tActivity2TaskCount.week_balance_count = 0;
                    hmTaskCount.put(tActivity2TaskCount.user_id,tActivity2TaskCount);
                }
                if (!hmNewTaskPoints.containsKey(tActivity2TaskPoints.user_id)) {
                    tActivity2TaskPointsList.add(tActivity2TaskPoints);
                    hmNewTaskPoints.put(tActivity2TaskPoints.user_id, tActivity2TaskPoints);
                }
            }
            boolean mondyStatus = isMonday(date);
            if (mondyStatus){
                log.info("Clear weekly data on Monday");
                for (TActivity2TaskPoints tActivity2TaskPoints : tActivity2TaskPointsList){
                    tActivity2TaskPoints.week_deposit = new BigDecimal(0.00000000);
                    tActivity2TaskPoints.week_sum_deposit = new BigDecimal(0.00000000);
                    tActivity2TaskPoints.week_deposit_integral = 0;
                    tActivity2TaskPoints.week_in_cash = new BigDecimal(0.00000000);

                    tActivity2TaskPoints.week_balance = new BigDecimal(0.00000000);
                    tActivity2TaskPoints.week_balance_integral = 0;

                    tActivity2TaskPoints.week_trading_volume = new BigDecimal(0.00000000);
                    tActivity2TaskPoints.week_sum_trading_volume = new BigDecimal(0.00000000);
                    tActivity2TaskPoints.week_trading_volume_integral = 0;

//                    tActivity2TaskPoints.week_invitation_state = 0;
                    tActivity2TaskPoints.week_invitation_integral = 0;

                    tActivity2TaskPoints.linea_deposit_integral = 0;
                    tActivity2TaskPoints.linea_deposit = new BigDecimal(0.00000000);
                    tActivity2TaskPoints.linea_sum_deposit = new BigDecimal(0.00000000);
                }
            }
            for (TUsers tUsers : users){
                TActivity2TaskPoints tActivity2TaskPoints = hmNewTaskPoints.get(tUsers.user_id);
                TActivity2TaskCount tActivity2TaskCount = hmTaskCount.get(tUsers.user_id);
                BigDecimal cashIn = hmUserCashInOutDate.get(tUsers.user_id + "_0");
                BigDecimal cashOut = hmUserCashInOutDate.get(tUsers.user_id + "_1");
                BigDecimal remainingAmount = BigDecimal.ZERO;
                if (cashIn == null){
                    cashIn = BigDecimal.ZERO;
                }
                if (cashOut == null){
                    cashOut = BigDecimal.ZERO;
                }
                tActivity2TaskPoints.week_in_cash = tActivity2TaskPoints.week_in_cash.add(cashIn);
                tActivity2TaskPoints.sum_in_cash = tActivity2TaskPoints.sum_in_cash.add(cashIn);
                remainingAmount = cashIn.subtract(cashOut);
                if (remainingAmount != null) {
                    BigDecimal sumWeekCashIn = tActivity2TaskPoints.week_sum_deposit.add(remainingAmount);
                    JSONArray weekdeposit = jsonObject.getJSONArray("weekdeposit");
                    for (Object o : weekdeposit) {
                        JSONObject jsonObject1 = (JSONObject) o;
                        BigDecimal deposit = jsonObject1.getBigDecimal("deposit");
                        int integral = jsonObject1.getIntValue("integral");
                        if (sumWeekCashIn.compareTo(deposit) >= 0 && tActivity2TaskPoints.week_deposit.compareTo(deposit) < 0) {
                            tActivity2TaskPoints.week_deposit_integral = tActivity2TaskPoints.week_deposit_integral + integral;
                            tActivity2TaskPoints.week_integral = tActivity2TaskPoints.week_integral + integral;
                            tActivity2TaskPoints.week_deposit = deposit;
                        }
                    }
                    tActivity2TaskPoints.week_sum_deposit = sumWeekCashIn;


                    BigDecimal sumDeposit = tActivity2TaskPoints.sum_deposit.add(remainingAmount);
                    JSONArray deposits = jsonObject.getJSONArray("deposit");
                    for (Object o : deposits) {
                        JSONObject jsonObject1 = (JSONObject) o;
                        BigDecimal deposit = jsonObject1.getBigDecimal("deposit");
                        int integral = jsonObject1.getIntValue("integral");
                        int level = jsonObject1.getIntValue("level");
                        if (level == 1) {
                            if (sumDeposit.compareTo(deposit) >= 0 && tActivity2TaskPoints.deposit.compareTo(deposit) < 0) {
                                tActivity2TaskPoints.deposit_integral1 = tActivity2TaskPoints.deposit_integral1 + integral;
                                tActivity2TaskPoints.deposit = deposit;
                            }
                        } else if (level == 2) {
                            if (sumDeposit.compareTo(deposit) >= 0 && tActivity2TaskPoints.deposit.compareTo(deposit) < 0) {
                                tActivity2TaskPoints.deposit_integral2 = tActivity2TaskPoints.deposit_integral2 + integral;
                                tActivity2TaskPoints.deposit = deposit;
                            }
                        }

                    }
                    tActivity2TaskPoints.sum_deposit = sumDeposit;
                }
                if (hmUserExecOrderDate.containsKey(tUsers.user_id)){
                    BigDecimal trading_volume = hmUserExecOrderDate.get(tUsers.user_id);
                    if (trading_volume != null) {
                        BigDecimal sumWeekvolume = tActivity2TaskPoints.week_sum_trading_volume.add(trading_volume);
                        JSONArray weekvolumes = jsonObject.getJSONArray("weekvolume");
                        for (Object o : weekvolumes) {
                            JSONObject jsonObject1 = (JSONObject) o;
                            BigDecimal deposit = jsonObject1.getBigDecimal("tradingvolume");
                            int integral = jsonObject1.getIntValue("integral");
                            if (sumWeekvolume.compareTo(deposit) >= 0 && tActivity2TaskPoints.week_trading_volume.compareTo(deposit) < 0) {
                                tActivity2TaskPoints.week_trading_volume_integral = tActivity2TaskPoints.week_trading_volume_integral + integral;
                                tActivity2TaskPoints.week_integral = tActivity2TaskPoints.week_integral + integral;
                                tActivity2TaskPoints.week_trading_volume = deposit;
                            }
                        }
                        tActivity2TaskPoints.week_sum_trading_volume = sumWeekvolume;

                        BigDecimal sumTradingVolume = tActivity2TaskPoints.sum_trading_volume.add(trading_volume);
                        JSONArray tradingvolumes = jsonObject.getJSONArray("tradingvolume");
                        for (Object o : tradingvolumes) {
                            JSONObject jsonObject1 = (JSONObject) o;
                            BigDecimal deposit = jsonObject1.getBigDecimal("tradingvolume");
                            int integral = jsonObject1.getIntValue("integral");
                            int level = jsonObject1.getIntValue("level");
                            if (level == 1) {
                                if (sumTradingVolume.compareTo(deposit) >= 0 && tActivity2TaskPoints.trading_volume.compareTo(deposit) < 0) {
                                    tActivity2TaskPoints.trading_volume_integral1 = tActivity2TaskPoints.trading_volume_integral1 + integral;
                                    tActivity2TaskPoints.trading_volume = deposit;
                                }
                            } else if (level == 2) {
                                if (sumTradingVolume.compareTo(deposit) >= 0 && tActivity2TaskPoints.trading_volume.compareTo(deposit) < 0) {
                                    tActivity2TaskPoints.trading_volume_integral2 = tActivity2TaskPoints.trading_volume_integral2 + integral;
                                    tActivity2TaskPoints.trading_volume = deposit;
                                }
                            }
                        }
                        tActivity2TaskPoints.sum_trading_volume = sumTradingVolume;
                    }
                }
                int weekbalancecount = jsonObject.getIntValue("weekbalancecount");
                if (tActivity2TaskCount.week_balance_count >= weekbalancecount){
                    if (hmUsersRpt.containsKey(tUsers.user_id)){
                        TUsersRpt tUsersRpt = hmUsersRpt.get(tUsers.user_id);
                        BigDecimal total_assets = tUsersRpt.total_assets;
                        if (total_assets != null) {
                            JSONArray weekbalances = jsonObject.getJSONArray("weekbalance");
                            for (Object o : weekbalances) {
                                JSONObject jsonObject1 = (JSONObject) o;
                                BigDecimal balance = jsonObject1.getBigDecimal("balance");
                                int integral = jsonObject1.getIntValue("integral");
                                if (total_assets.compareTo(balance) >= 0 && tActivity2TaskPoints.week_balance.compareTo(balance) < 0) {
                                    tActivity2TaskPoints.week_balance_integral = tActivity2TaskPoints.week_balance_integral + integral;
                                    tActivity2TaskPoints.week_integral = tActivity2TaskPoints.week_integral + integral;
                                    tActivity2TaskPoints.week_balance = balance;
                                }
                            }
                        }
                    }
                }

                if (hmUsersRpt.containsKey(tUsers.user_id)) {
                    TUsersRpt tUsersRpt = hmUsersRpt.get(tUsers.user_id);
                    BigDecimal total_assets = tUsersRpt.total_assets;
                    int balancecount = jsonObject.getIntValue("balancecount");
                    if (total_assets != null) {
                        JSONArray accounttotalbalances = jsonObject.getJSONArray("accounttotalbalance");
                        for (Object o : accounttotalbalances) {
                            JSONObject jsonObject1 = (JSONObject) o;
                            BigDecimal balance = jsonObject1.getBigDecimal("balance");
                            int integral = jsonObject1.getIntValue("integral");
                            int level = jsonObject1.getIntValue("level");
                            if (level == 1) {
                                if (tActivity2TaskCount.account_balance_count1 >= balancecount) {
                                    if (total_assets.compareTo(balance) >= 0 && tActivity2TaskPoints.account_total_balance.compareTo(balance) < 0) {
                                        tActivity2TaskPoints.account_total_balance_integral1 = tActivity2TaskPoints.account_total_balance_integral1 + integral;
                                        tActivity2TaskPoints.account_total_balance = balance;
                                    }
                                }
                            } else if (level == 2) {
                                if (tActivity2TaskCount.account_balance_count2 >= balancecount) {
                                    if (total_assets.compareTo(balance) >= 0 && tActivity2TaskPoints.account_total_balance.compareTo(balance) < 0) {
                                        tActivity2TaskPoints.account_total_balance_integral2 = tActivity2TaskPoints.account_total_balance_integral2 + integral;
                                        tActivity2TaskPoints.account_total_balance = balance;
                                    }
                                }
                            }
                        }

                    }
                }

                if (hmUserChainIdCashInDate.containsKey(tUsers.user_id)){
                    BigDecimal lineaCashIn = hmUserChainIdCashInDate.get(tUsers.user_id);
                    if (lineaCashIn != null){
                        BigDecimal sumLineaCashIn = tActivity2TaskPoints.linea_sum_deposit.add(lineaCashIn);
                        JSONArray weekdeposit = jsonObject.getJSONArray("lineadeposit");
                        for (Object o : weekdeposit){
                            JSONObject jsonObject1 = (JSONObject)o;
                            BigDecimal deposit = jsonObject1.getBigDecimal("deposit");
                            int integral = jsonObject1.getIntValue("integral");
                            if (sumLineaCashIn.compareTo(deposit) >= 0 && tActivity2TaskPoints.linea_deposit.compareTo(deposit) < 0){
                                tActivity2TaskPoints.linea_deposit_integral = tActivity2TaskPoints.linea_deposit_integral + integral;
                                tActivity2TaskPoints.week_integral = tActivity2TaskPoints.week_integral + integral;
                                tActivity2TaskPoints.linea_deposit = deposit;
                            }
                        }
                        tActivity2TaskPoints.linea_sum_deposit = sumLineaCashIn;
                    }
                }
                if (hmNftOwners.containsKey(tUsers.user_name.toLowerCase())  || hmNftOwners.containsKey(tUsers.name.toLowerCase())){
                    int nftowner_integral= jsonObject.getIntValue("nftowner");
                    tActivity2TaskPoints.og_gem_nft_integral = nftowner_integral;
                }else {
                    tActivity2TaskPoints.og_gem_nft_integral = 0;
                }
            }
            for (TActivity2TaskPoints tActivity2TaskPoints : tActivity2TaskPointsList){
                JSONArray weekinvites = jsonObject.getJSONArray("weekinvitation");
                for (Object o : weekinvites){
                    JSONObject jsonObject1 = (JSONObject)o;
                    BigDecimal invitation = jsonObject1.getBigDecimal("invitation");
                    int integral = jsonObject1.getIntValue("integral");
                    int level = jsonObject1.getIntValue("level");
                    if (level == 1) {
                        if (tActivity2TaskPoints.week_in_cash.compareTo(invitation) >= 0 && tActivity2TaskPoints.week_invitation_state == 0) {
                            TUsers tUsers = hmReferrer.get(tActivity2TaskPoints.user_id);
                            if (StringUtils.isNotEmpty(tUsers.referrer) && hmReferrer.containsKey(tUsers.referrer)){
                                TActivity2TaskPoints referrerTaskPoints = hmNewTaskPoints.get(tUsers.referrer);
                                if (referrerTaskPoints != null) {
                                    tActivity2TaskPoints.week_invitation_state = 1;
                                    referrerTaskPoints.week_integral = referrerTaskPoints.week_integral + integral;
                                    referrerTaskPoints.week_invitation_integral = referrerTaskPoints.week_invitation_integral + integral;
                                }

                            }

                        }
                    }
                }

                JSONArray invitations = jsonObject.getJSONArray("invitation");
                for (Object o : invitations){
                    JSONObject jsonObject1 = (JSONObject)o;
                    BigDecimal invitation = jsonObject1.getBigDecimal("invitation");
                    int integral = jsonObject1.getIntValue("integral");
                    int level = jsonObject1.getIntValue("level");
                    if (level == 1) {
                        if (tActivity2TaskPoints.sum_in_cash.compareTo(invitation) >= 0 && tActivity2TaskPoints.invite_state == 0) {
                            TUsers tUsers = hmReferrer.get(tActivity2TaskPoints.user_id);
                            if (StringUtils.isNotEmpty(tUsers.referrer) && hmReferrer.containsKey(tUsers.referrer)){
                                TActivity2TaskPoints referrerTaskPoints = hmNewTaskPoints.get(tUsers.referrer);
                                if (referrerTaskPoints != null) {
                                    tActivity2TaskPoints.invite_state = 1;
                                    referrerTaskPoints.invite_integral = referrerTaskPoints.invite_integral + integral;
                                }

                            }

                        }
                    }
                }

            }
            List<TUsersRpt> tUsersRptList = new ArrayList<>();
            List<TActivity2TaskPointsDay> tActivity2TaskPointsDays = new ArrayList<>();
            for (TActivity2TaskPoints tActivity2TaskPoints : tActivity2TaskPointsList){

                int sum_integral = tActivity2TaskPoints.week_integral + tActivity2TaskPoints.og_gem_nft_integral+ tActivity2TaskPoints.deposit_integral1+ tActivity2TaskPoints.deposit_integral2+
                        tActivity2TaskPoints.account_total_balance_integral1 + tActivity2TaskPoints.account_total_balance_integral2 +
                        tActivity2TaskPoints.trading_volume_integral1 + tActivity2TaskPoints.trading_volume_integral2+tActivity2TaskPoints.invite_integral + tActivity2TaskPoints.share_integral;
                if (hmUsersRpt.containsKey(tActivity2TaskPoints.user_id)) {
                    TUsersRpt rpt = hmUsersRpt.get(tActivity2TaskPoints.user_id);
                    rpt.activity2_integral = sum_integral;
                    tUsersRptList.add(rpt);
                }
                if (hmUsersRptDay.containsKey(tActivity2TaskPoints.user_id)) {
                    TUsersRptDay rptDay = hmUsersRptDay.get(tActivity2TaskPoints.user_id);
                    rptDay.activity2_integral = sum_integral;
                }
                TActivity2TaskCount tActivity2TaskCount = hmTaskCount.get(tActivity2TaskPoints.user_id);
                TActivity2TaskPointsDay tActivity2TaskPointsDay = new TActivity2TaskPointsDay();
                tActivity2TaskPointsDay.trade_date = date;
                BeanUtils.copyProperties(tActivity2TaskPoints,tActivity2TaskPointsDay);
                tActivity2TaskPointsDay.week_balance_count = tActivity2TaskCount.week_balance_count;
                tActivity2TaskPointsDay.account_balance_count1 = tActivity2TaskCount.account_balance_count1;
                tActivity2TaskPointsDay.account_balance_count2 = tActivity2TaskCount.account_balance_count2;
                tActivity2TaskPointsDays.add(tActivity2TaskPointsDay);
            }



            JobDao.deleteTActivity2TaskPointsDay(date);
            JobDao.insertTActivity2TaskPointsDay(tActivity2TaskPointsDays);

            boolean isMondaysStatus = isSunday(date);
            if (isMondaysStatus){
                log.info("Clear weekly account accumulation on Sunday");
                List<TActivity2TaskCount> tActivity2TaskCounts = new ArrayList<>();
                for (TActivity2TaskPoints tActivity2TaskPoints : tActivity2TaskPointsList){
                    TActivity2TaskCount tActivity2TaskCount = hmTaskCount.get(tActivity2TaskPoints.user_id);
                    tActivity2TaskCount.week_balance_count = 0;

                    tActivity2TaskCounts.add(tActivity2TaskCount);
                }
                JobDao.deleteTActivity2TaskCount();
                JobDao.insertTActivity2TaskCount(tActivity2TaskCounts);
            }

            JobDao.deleteTActivity2TaskPoints();
            JobDao.insertTActivity2TaskPoints(tActivity2TaskPointsList);
            if (tUsersRptList.size() >0){
                ActivityIntegralUtils.updateActivityIntegral(tUsersRptList, hmUsersRptDay);
                List<TUsersRptDay> insertUsersRptDayLt = new ArrayList<>();
                insertUsersRptDayLt.addAll(hmUsersRptDay.values());
                JobDao.deleteTUsersRptDay(date);
                JobDao.insertUsersRptDay(insertUsersRptDayLt);
                JobDao.deleteTUsersRpt();
                JobDao.insertUsersRpt(tUsersRptList);
//                JobDao.updateActivity2Integral(tUsersRptList, date);
//                ActivityIntegralUtils.updateActivity(tUsersRptList,date);
            }
        }
    }


    public static String getDate() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String date;
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, -1);
        date = format.format(cal.getTime());
        return date;
    }

    public static String getLastDate() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String date;
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, -2);
        date = format.format(cal.getTime());
        return date;
    }

    public boolean isSunday(String dateStr) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse(dateStr);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date); 
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        boolean status = false;
        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            status = true;
        }
        return status;
    }



    public boolean isMonday(String dateStr) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse(dateStr);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date); 
        boolean status = false;
        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
            status = true;
        }
        return status;
    }

    private HashMap<String, BigDecimal> getAllUsersSumExecOrder(String date) throws Exception {
        HashMap<String, BigDecimal> hm = new HashMap<String, BigDecimal>();
        String sql = String.format(
                "SELECT user_id,sum(exec_value) exec_value FROM dc_orders_execorders group by user_id");
        if (StringUtils.isNoneBlank(date)) {
            sql = String.format(
                    "SELECT user_id,sum(exec_value) exec_value FROM dc_orders_execorders where left(create_time,10)='%s' group by user_id",
                    date);
        }
        List<TOrdersExecorders> cusLt = DBUtils.queryListThrowsException(sql, null, TOrdersExecorders.class);
        for (TOrdersExecorders tOrdersExecorders : cusLt) {
            hm.put(tOrdersExecorders.user_id, tOrdersExecorders.exec_value);
        }
        log.info("getAllUsersSumExecOrder date:{},size:{}", date, cusLt.size());

        return hm;
    }


    private List<TUsers> getAllUsers() throws Exception {
        String selectAllUser = "select * from dc_users where user_type='1'";
        List<TUsers> users = DBUtils.queryListThrowsException(selectAllUser, new Object[] {}, TUsers.class);
        log.info("getAllUsers size:{}", users.size());
        return users;
    }



    private HashMap<String, TUsersRpt> getUsersRpt() throws Exception {
        HashMap<String, TUsersRpt> hm = new HashMap();
        String selectCus="select * from dc_users_rpt";
        List<TUsersRpt> cusLt = DBUtils.queryListThrowsException(selectCus, null, TUsersRpt.class);
        for (TUsersRpt tKolCusRetSerCount : cusLt) {
            hm.put(tKolCusRetSerCount.user_id+"", tKolCusRetSerCount);
        }
        log.info("getUsersRpt size:{}",cusLt.size());

        return hm;
    }


    private HashMap<String, TUsersRptDay> getAllUsersRptDay(String date) throws Exception {
        HashMap<String, TUsersRptDay> hm = new HashMap<String, TUsersRptDay>();
        String selectCus =String.format( "select * from dc_users_rpt_day where  trade_date='%s'",date);
        List<TUsersRptDay> cusLt = DBUtils.queryListThrowsException(selectCus, null, TUsersRptDay.class);
        for (TUsersRptDay tKolCusRetSerCount : cusLt) {
            hm.put(tKolCusRetSerCount.user_id, tKolCusRetSerCount);
        }
        log.info("getAllUsersRptDay:{},size:{}", date,cusLt.size());

        return hm;
    }

    private HashMap<String, TNftOwners> getTNftOwners() throws Exception {
        HashMap<String, TNftOwners> hm = new HashMap();
        String selectCus="select * from dc_nft_owners";
        List<TNftOwners> cusLt = DBUtils.queryListThrowsException(selectCus, null, TNftOwners.class);
        for (TNftOwners tNftOwners : cusLt) {
            hm.put(tNftOwners.owner.toLowerCase(), tNftOwners);
        }
        log.info("getTNftOwners size:{}",cusLt.size());

        return hm;
    }
}
