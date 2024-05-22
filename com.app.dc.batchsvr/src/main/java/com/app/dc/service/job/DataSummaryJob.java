package com.app.dc.service.job;

import com.app.common.db.DBUtils;
import com.app.dc.TAuditLog;
import com.app.dc.entity.*;

import com.app.dc.util.Consts;
import com.app.dc.utils.FeeRateUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

@Slf4j
public class DataSummaryJob implements Job {

    private boolean runFlag = false;

    int retryCount = 3;
    int currentCount = 1;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("DataSummaryJob execute");
        try {
            dataSummaryJob();
        } catch (Exception e) {
            log.error(e+e.getMessage());
            if (currentCount <= retryCount){
                log.info("DataSummaryJob Re execute,currentCount:{}",currentCount);
                runFlag = false;
                currentCount = currentCount + 1;
                execute(null);
            }
        }
    }
    public synchronized void dataSummaryJob() throws Exception {
        if (!runFlag) {
            runFlag = true;
            updateDataSummary();
            runFlag = false;
            log.info("DataSummaryJob Job success");
        } else {
            log.warn("DataSummaryJob job is run.");

        }
    }

    private void updateDataSummary() throws Exception {
        String date = getDate();
//        String date = "2023-09-27";
        List<TUsers> tUsersList = getTUsers();
        List<TOrdersExecorders> ordersExecordersList = getAllSumExecOrderExByUserAndSymbol(date);
        List<TCashLog> cashLogs = getAllUsersSumCashInOut(date);
        HashMap<String, TAuditLogEx> hmTAuditLogEx = getLoginTAuditLog(date);
        HashMap<String, TKolPartner> hmTKolPartner = getAllTKolPartner();
        TSummaryDataDay tSummaryDataDay = new TSummaryDataDay();
        tSummaryDataDay.trade_date = date;
        int newUserLoginCount = 0;
        int oldUserLoginCount = 0;
        int kolInviteNumber = 0;
        int inviteNumber = 0;
        List<TSummaryTransactionDataDay> transactionDataDays = new ArrayList<>();
        for (TOrdersExecorders tOrdersExecorders : ordersExecordersList){
            TSummaryTransactionDataDay dataDay = new TSummaryTransactionDataDay();
            dataDay.trade_date = date;
            dataDay.symbol = tOrdersExecorders.symbol;
            dataDay.user_id = tOrdersExecorders.user_id;
            dataDay.trading_volume = tOrdersExecorders.exec_value;
            transactionDataDays.add(dataDay);
        }
        HashMap<String,TSummaryCashDataDay> summaryCashDataDayHashMap = new HashMap<>();
        for (TCashLog tcashLog : cashLogs) {
            TSummaryCashDataDay cashDataDay = summaryCashDataDayHashMap.get(tcashLog.user_id);
            if (cashDataDay == null){
                cashDataDay = new TSummaryCashDataDay();
                cashDataDay.user_id = tcashLog.user_id;
                cashDataDay.trade_date = date;
                summaryCashDataDayHashMap.put(tcashLog.user_id,cashDataDay);
            }
            if (tcashLog.side == 0){
                cashDataDay.deposit_amount = cashDataDay.deposit_amount.add(tcashLog.amount);
            }else if (tcashLog.side == 1) {
                cashDataDay.withdraw_amount = cashDataDay.withdraw_amount.add(tcashLog.amount);
            }
        }

        for (TUsers user : tUsersList){
            if (StringUtils.isNotEmpty(user.create_time) && user.create_time.length() >= 10){
                String create_time = user.create_time.substring(0,10);
                if (hmTAuditLogEx.containsKey(user.user_id)){
                    if (create_time.equals(date)){
                        newUserLoginCount = newUserLoginCount + 1;
                    }else {
                        oldUserLoginCount = oldUserLoginCount + 1;
                    }
                }
                if (create_time.equals(date)) {
                    if (StringUtils.isNotEmpty(user.referrer)) {
                        if (hmTKolPartner.containsKey(user.referrer)) {
                            kolInviteNumber = kolInviteNumber + 1;
                        } else {
                            inviteNumber = inviteNumber + 1;
                        }
                    }
                }
            }
        }
        tSummaryDataDay.invite_number = inviteNumber;
        tSummaryDataDay.kol_invite_number = kolInviteNumber;
        tSummaryDataDay.new_user_login_number = newUserLoginCount;
        tSummaryDataDay.old_user_login_number = oldUserLoginCount;
        deleteTSummaryDataDay(date);
        insertTSummaryDataDay(tSummaryDataDay);
        List<TSummaryCashDataDay> summaryCashDataDayList = new ArrayList<>();
        summaryCashDataDayList.addAll(summaryCashDataDayHashMap.values());
        deleteTSummaryCashDataDay(date);
        insertTSummaryCashDataDay(summaryCashDataDayList);
        deleteTSummaryTransactionDataDay(date);
        insertTSummaryTransactionDataDay(transactionDataDays);
    }

    private static String getDate() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String date;
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, -1);
        date = format.format(cal.getTime());
        return date;
    }


    private List<TUsers> getTUsers() throws Exception {
        String sql = "SELECT * FROM dc_users where user_type='1' and enable='1'";
        List<TUsers> cusLt = DBUtils.queryListThrowsException(sql, null, TUsers.class);
        log.info("getTUsers size:{}", cusLt.size());
        return cusLt;
    }

    private HashMap<String, TKolPartner> getAllTKolPartner() throws Exception {
        HashMap<String, TKolPartner> hm = new HashMap<String, TKolPartner>();

        String selectAllUser = "select * from dc_kol_partner where status='1'";
        List<TKolPartner> users = DBUtils.queryListThrowsException(selectAllUser, new Object[] {}, TKolPartner.class);
        for (TKolPartner tKolPartner : users) {
            hm.put(tKolPartner.user_id, tKolPartner);
        }
        log.info("getAllTKolPartner size:{}", users.size());
        return hm;
    }

    private HashMap<String, TAuditLogEx> getLoginTAuditLog(String date) throws Exception {
        HashMap<String, TAuditLogEx> hm = new HashMap<String, TAuditLogEx>();
        String sql = String.format("select user_id, count(user_id) as count  from dc_audit_log where type='1' and status='1' and terminal <> 'Manager' and left(create_time,10)='%s'  group by user_id", date);;
        log.info("getLoginTAuditLog sql:{}",sql);
        List<TAuditLogEx> cusLt = DBUtils.queryListThrowsException(sql, null, TAuditLogEx.class);
        for (TAuditLogEx tAuditLog : cusLt) {
            hm.put(tAuditLog.user_id, tAuditLog);
        }
        log.info("getTAuditLog size:{}", cusLt.size());
        return hm;
    }


    private List<TCashLog> getAllUsersSumCashInOut(String date) throws Exception {
         String sql = String.format(
                    "SELECT user_id,sum(amount) amount,side FROM dc_cash_log  where left(update_time,10)='%s' and status='1' and cash_type='1' group by user_id,side",
                    date);
        log.info("getAllUsersSumCashInOut sql:{}",sql);
        List<TCashLog> cusLt = DBUtils.queryListThrowsException(sql, null, TCashLog.class);
        log.info("getAllUsersSumCashInOut date:{},size:{}", date, cusLt.size());
        return cusLt;
    }

    private List<TOrdersExecorders> getAllSumExecOrderExByUserAndSymbol(String date) throws Exception {
         String sql = String.format(
                    "select symbol,user_id,sum(exec_value) exec_value,sum(closed_pnl) closed_pnl,sum(exec_fee) exec_fee  FROM dc_orders_execorders where left(create_time,10)='%s' group by user_id,symbol",
                    date);
        log.info("getAllSumExecOrderExByUserAndSymbol sql:{}",sql);
        List<TOrdersExecorders> cusLt = DBUtils.queryListThrowsException(sql, null, TOrdersExecorders.class);
        log.info("getAllSumExecOrderExByUserAndSymbol date:{},size:{}", date, cusLt.size());

        return cusLt;
    }


    private void deleteTSummaryDataDay(String date) throws Exception {
        DBUtils.update(String.format("delete from dc_summary_data_day where trade_date='%s'", date), new Object[] {});
        log.info("deleteTSummaryDataDay :{} success", date);
    }

    private void insertTSummaryDataDay(TSummaryDataDay dataDay) throws Exception {
        log.info("insertTSummaryDataDay begin");
        DBUtils.insert(dataDay, "dc_summary_data_day");
        log.info("insertTSummaryDataDay end");

    }


    private void deleteTSummaryCashDataDay(String date) throws Exception {
        DBUtils.update(String.format("delete from dc_summary_cash_data_day where trade_date='%s'", date), new Object[] {});
        log.info("deleteTSummaryCashDataDay :{} success", date);
    }

    private void insertTSummaryCashDataDay(List<TSummaryCashDataDay> lt) throws Exception {
        log.info("insertTSummaryCashDataDay size:{} begin", lt.size());
        DBUtils.insertList(lt, "dc_summary_cash_data_day");
        log.info("insertTSummaryCashDataDay size:{} end", lt.size());

    }


    private void deleteTSummaryTransactionDataDay(String date) throws Exception {
        DBUtils.update(String.format("delete from dc_summary_transaction_data_day where trade_date='%s'", date), new Object[] {});
        log.info("deleteTSummaryTransactionDataDay :{} success", date);
    }

    private void insertTSummaryTransactionDataDay(List<TSummaryTransactionDataDay> lt) throws Exception {
        log.info("insertTSummaryTransactionDataDay size:{} begin", lt.size());
        DBUtils.insertList(lt, "dc_summary_transaction_data_day");
        log.info("insertTSummaryTransactionDataDay size:{} end", lt.size());

    }

}
