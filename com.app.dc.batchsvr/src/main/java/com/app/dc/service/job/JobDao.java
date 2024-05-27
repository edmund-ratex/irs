package com.app.dc.service.job;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.app.common.db.DBUtils;
import com.app.common.utils.JsonUtils;
import com.app.common.utils.StringUtil;
import com.app.dc.entity.*;
import com.app.dc.util.Consts;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
public class JobDao {
    public static List<AirdropActivityDate> getAirdropActivityDate() throws Exception {
        List<AirdropActivityDate> airdropActivityDates = new ArrayList<>();
        String sql = "select * from dc_system_parameter where param_key='airdrop_activity'";
        List<TSystemParameter> list = DBUtils.queryListThrowsException(sql, null, TSystemParameter.class);
        if (list != null && list.size() >0){
            TSystemParameter parameter = list.get(0);
            airdropActivityDates = JsonUtils.Deserialize2(parameter.param_value,AirdropActivityDate.class);
        }
        log.info("airdropActivityDates size:{}",airdropActivityDates == null ? 0 : airdropActivityDates.size());
        return airdropActivityDates;
    }

    public static JSONObject  getAirdrop2Param() throws Exception {
        JSONObject jsonObject = null;
        String sql = "select * from dc_system_parameter where param_key='airdrop_activity_2_param'";
        List<TSystemParameter> list = DBUtils.queryListThrowsException(sql, null, TSystemParameter.class);
        if (list != null && list.size() >0){
            TSystemParameter parameter = list.get(0);
            log.info("airdrop_activity_2_param:{}",parameter.param_value);
            jsonObject = JSON.parseObject(parameter.param_value);
        }
        return jsonObject;
    }

    public static HashMap<String, BigDecimal> getAllUsersSumCashInOut(String date) throws Exception {
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


    public static HashMap<String, BigDecimal> getAllUsersSumCashInByChainId(String date,String chainId) throws Exception {
        HashMap<String, BigDecimal> hm = new HashMap<String, BigDecimal>();
        List<TCashLog> cusLt = new ArrayList<>();
        if (StringUtil.isNotEmpty(chainId)) {
            String sql = String.format(
                    "SELECT user_id,sum(amount) amount FROM dc_cash_log where  status='1' and cash_type='1' and side=0  and chain_id in (%s) group by user_id", chainId);
            if (StringUtils.isNoneBlank(date)) {
                sql = String.format(
                        "SELECT user_id,sum(amount) amount FROM dc_cash_log  where left(update_time,10)='%s' and status='1' and side=0  and chain_id in (%s) and cash_type='1' group by user_id",
                        date, chainId);
            }

            cusLt = DBUtils.queryListThrowsException(sql, null, TCashLog.class);
            for (TCashLog tcashLog : cusLt) {
                hm.put(tcashLog.user_id, tcashLog.amount);
            }
        }
        log.info("getAllUsersSumCashInByChainId date:{},size:{}", date, cusLt.size());

        return hm;
    }


    public static HashMap<String, TMarkPriceDay> getTMarkPriceDay(String date) throws Exception {
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

    public static HashMap<String, TActivity2TaskPoints> getLastActivity2TaskPoints(String date) throws Exception {
        HashMap<String, TActivity2TaskPoints> hm = new HashMap<String, TActivity2TaskPoints>();
        String selectAllUser = String.format("SELECT * from dc_activity2_task_points_day where trade_date='%s'",date);
        List<TActivity2TaskPoints> taskPoints = DBUtils.queryListThrowsException(selectAllUser, new Object[] {}, TActivity2TaskPoints.class);
        for (TActivity2TaskPoints tActivity2TaskPoints : taskPoints) {
            hm.put(tActivity2TaskPoints.user_id, tActivity2TaskPoints);
        }
        log.info("getLastActivity2TaskPoints size:{}", taskPoints.size());
        return hm;
    }


    public static HashMap<String, TActivity2TaskPoints> getActivity2TaskPoints() throws Exception {
        HashMap<String, TActivity2TaskPoints> hm = new HashMap<String, TActivity2TaskPoints>();
        String selectAllUser = "SELECT * from dc_activity2_task_points";
        List<TActivity2TaskPoints> taskPoints = DBUtils.queryListThrowsException(selectAllUser, new Object[] {}, TActivity2TaskPoints.class);
        for (TActivity2TaskPoints tActivity2TaskPoints : taskPoints) {
            hm.put(tActivity2TaskPoints.user_id, tActivity2TaskPoints);
        }
        log.info("getActivity2TaskPoints size:{}", taskPoints.size());
        return hm;
    }

    public static HashMap<String, TActivity2TaskCount> getTActivity2TaskCount() throws Exception {
        HashMap<String, TActivity2TaskCount> hm = new HashMap<String, TActivity2TaskCount>();
        String selectAllUser = "SELECT * from dc_activity2_task_count";
        List<TActivity2TaskCount> taskPoints = DBUtils.queryListThrowsException(selectAllUser, new Object[] {}, TActivity2TaskCount.class);
        for (TActivity2TaskCount tActivity2TaskPoints : taskPoints) {
            hm.put(tActivity2TaskPoints.user_id, tActivity2TaskPoints);
        }
        log.info("getTActivity2TaskCount size:{}", taskPoints.size());
        return hm;
    }


    public static void updateActivity2Integral(List<TUsersRpt> tUsersRpts,String date) throws Exception {
        String sql = "UPDATE dc_users_rpt set activity2_integral=? where user_id=?";
        List<Object[]> args = new ArrayList<>();
        for (TUsersRpt tUsersRpt : tUsersRpts){
            Object[] o = new Object[2];
            o[0]=tUsersRpt.activity2_integral;
            o[1]= tUsersRpt.user_id;
            args.add(o);
        }
        String sql1 = "UPDATE dc_users_rpt_day set activity2_integral=? where user_id=? and trade_date=?";
        List<Object[]> args1 = new ArrayList<>();
        for (TUsersRpt tUsersRpt : tUsersRpts){
            Object[] o = new Object[3];
            o[0]=tUsersRpt.activity2_integral;
            o[1]= tUsersRpt.user_id;
            o[2]= date;
            args1.add(o);
        }
        DBUtils.updateList(sql,args);
        DBUtils.updateList(sql1,args1);
    }

    public static void deleteTActivity2TaskCount() throws Exception {

        DBUtils.update("delete from dc_activity2_task_count", new Object[] {});
        log.info("deleteTActivity2TaskCount success");

    }
    public static void insertTActivity2TaskCount(List<TActivity2TaskCount> lt) throws Exception {
        DBUtils.insertList(lt, "dc_activity2_task_count");
        log.info("insertTActivity2TaskCount size:{}", lt.size());

    }

    public static void deleteTActivity2TaskPoints() throws Exception {

        DBUtils.update("delete from dc_activity2_task_points", new Object[] {});
        log.info("deleteTActivity2TaskPoints success");

    }
    public static void insertTActivity2TaskPoints(List<TActivity2TaskPoints> lt) throws Exception {
        DBUtils.insertList(lt, "dc_activity2_task_points");
        log.info("insertTActivity2TaskPoints size:{}", lt.size());

    }



    public static void deleteTActivity2TaskPointsDay(String date) throws Exception {
        DBUtils.update(String.format("delete from dc_activity2_task_points_day where trade_date='%s'", date), new Object[] {});
        log.info("deleteTActivity2TaskPointsDay date:{} success", date);

    }
    public static void insertTActivity2TaskPointsDay(List<TActivity2TaskPointsDay> lt) throws Exception {
        DBUtils.insertList(lt, "dc_activity2_task_points_day");
        log.info("insertTActivity2TaskPointsDay size:{}", lt.size());

    }

    public static void deleteTUsersRptDay(String date) throws Exception {

        DBUtils.update(String.format("delete from dc_users_rpt_day where trade_date='%s'", date), new Object[] {});
        log.info("deleteTUsersRptDay :{} success", date);

    }

    public static void insertUsersRptDay(List<TUsersRptDay> lt) throws Exception {
        List<TUsersRptDay> list = new ArrayList<>();
        for (TUsersRptDay day : lt){
            list.add(day);
            if (list.size() > Consts.BachCount){
                DBUtils.insertList(list, "dc_users_rpt_day");
                log.info("insertUsersRptDay list:{}", list.size());
                list.clear();
            }
        }
        if (list.size() >0){
            DBUtils.insertList(list, "dc_users_rpt_day");
            log.info("insertUsersRptDay list:{}", list.size());
            list.clear();
        }
        log.info("insertUsersRptDay size:{}", lt.size());

    }

    public static void deleteTUsersRpt() throws Exception {

        DBUtils.update("delete from dc_users_rpt", new Object[] {});
        log.info("deleteTUsersRpt  success");

    }

    public static  void insertUsersRpt(List<TUsersRpt> lt) throws Exception {

        List<TUsersRpt> list = new ArrayList<>();
        for (TUsersRpt day : lt){
            list.add(day);
            if (list.size() > Consts.BachCount){
                DBUtils.insertList(list, "dc_users_rpt");
                log.info("insertUsersRpt size:{}", list.size());
                list.clear();
            }
        }
        if (list.size() > 0){
            DBUtils.insertList(list, "dc_users_rpt");
            log.info("insertUsersRpt size:{}", list.size());
            list.clear();
        }

        log.info("insertUsersRpt size:{}", lt.size());

    }
}
