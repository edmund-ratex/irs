package com.app.dc.service.job;

import com.app.common.db.DBUtils;
import com.app.common.utils.StringUtil;
import com.app.dc.entity.TKolPartner;
import com.app.dc.entity.TUsers;
import com.app.dc.po.TSymbol;
import com.app.dc.po.UserFeeRatePo;
import com.app.dc.utils.FeeRateUtils;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

@Slf4j
public class FeeDiscountRatioJob implements Job {

    private static boolean runFlag = false;

    @Setter
    private FeeRateUtils feeRateUtils;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("FeeDiscountRatioJob execute");
        try {
            if (feeRateUtils == null) {
                JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
                Object o = jobDataMap.get("Object");
                if (o instanceof Object[]){
                    Object[] objs = (Object[])o;
                    feeRateUtils = (FeeRateUtils)objs[0];
                    log.info("feeRateUtils:{}",feeRateUtils);
                }

            }
            feeDiscountRatioJob();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }


    public synchronized void feeDiscountRatioJob() throws Exception {
        if (!runFlag) {
            runFlag = true;
            updateUserFeeDiscountRatio();
            runFlag = false;
        } else {
            log.warn("FeeDiscountRatio job is run.");

        }
    }

    private void updateUserFeeDiscountRatio() throws Exception {
        String date = getDate();
        log.info("date:{}",date);
        List<TSymbol> tSymbolList = getDateTSymbol(date);
        if (tSymbolList != null && tSymbolList.size() >0){
            HashMap<String, TKolPartner> hmKolPartner = getTKolPartner();
            List<TUsers> tUsersList = getTUsers();
            for (TSymbol tSymbol : tSymbolList) {
                for (TUsers tUsers : tUsersList) {
                    UserFeeRatePo userFeeRatePo = new UserFeeRatePo();
                    userFeeRatePo.user_id = tUsers.user_id;
                    userFeeRatePo.sid = "";
                    userFeeRatePo.symbol = String.valueOf(tSymbol.id);
                    userFeeRatePo.maker_commission = tSymbol.maker_commission;
                    userFeeRatePo.taker_commission = tSymbol.taker_commission;
                    userFeeRatePo.feeRatio = null;
                    if (tUsers.fee_discount_ratio != null && tUsers.fee_discount_ratio.compareTo(BigDecimal.ZERO) >0) {
                        userFeeRatePo.feeRatio = tUsers.fee_discount_ratio;
                        if (StringUtil.isNotEmpty(tUsers.referrer) && hmKolPartner.containsKey(tUsers.referrer)) {
                            TKolPartner tKolPartner = hmKolPartner.get(tUsers.referrer);
                            if (tKolPartner.fee_discount_ratio != null && tUsers.fee_discount_ratio != null) {
                                if (tKolPartner.fee_discount_ratio.compareTo(tUsers.fee_discount_ratio) > 0) {
                                    userFeeRatePo.feeRatio = tKolPartner.fee_discount_ratio;
                                }
                            }
                        }
                        feeRateUtils.userFeeRate(userFeeRatePo);
                    }else{
                        if (StringUtil.isNotEmpty(tUsers.referrer) && hmKolPartner.containsKey(tUsers.referrer)) {
                            TKolPartner tKolPartner = hmKolPartner.get(tUsers.referrer);
                            if (tKolPartner !=null && tKolPartner.fee_discount_ratio != null && tKolPartner.fee_discount_ratio.compareTo(BigDecimal.ZERO) >0) {
                                userFeeRatePo.feeRatio = tKolPartner.fee_discount_ratio;
                                feeRateUtils.userFeeRate(userFeeRatePo);
                            }
                        }
                    }
                }
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


    private List<TSymbol> getDateTSymbol(String date) throws Exception {
        String sql = String.format("SELECT * FROM dc_symbol where left(create_time,10)='%s'",date);
        List<TSymbol> cusLt = DBUtils.queryListThrowsException(sql, null, TSymbol.class);
        log.info("getDateTSymbol date:{},size:{}", date, cusLt.size());
        return cusLt;
    }


    private List<TUsers> getTUsers() throws Exception {
        String sql = "SELECT * FROM dc_users where  user_type='1' and enable='1'";
        List<TUsers> cusLt = DBUtils.queryListThrowsException(sql, null, TUsers.class);
        log.info("getTUsers size:{}", cusLt.size());
        return cusLt;
    }


    private HashMap<String, TKolPartner> getTKolPartner() throws Exception {
        HashMap<String, TKolPartner> hm = new HashMap<String, TKolPartner>();
        String sql = "SELECT * FROM dc_kol_partner where status='1'";
        List<TKolPartner> cusLt = DBUtils.queryListThrowsException(sql, null, TKolPartner.class);
        for (TKolPartner tKolPartner : cusLt) {
            hm.put(tKolPartner.user_id, tKolPartner);
        }
        log.info("getTKolPartner size:{}", cusLt.size());
        return hm;
    }
}
