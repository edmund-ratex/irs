package com.app.dc.service.job;

import com.app.common.db.DBUtils;
import com.app.dc.entity.TKolPartner;
import com.app.dc.entity.TKolRptMonth;
import com.app.dc.entity.TKolRuleDetail;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
public class KolGradeJob implements Job {

    private static boolean runFlag = false;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("KolGradeJob execute");
        try {
            KolGradeJob();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    public synchronized void KolGradeJob() throws Exception {
        if (!runFlag) {
            runFlag = true;
            try {
                updateKolGrade();
                log.info("KolGrade job success");
            } catch (Exception e) {
                log.error("updateKolGrade error:{}",e+e.getMessage());
            }finally {
                runFlag = false;
            }
        } else {
            log.warn("KolGrade job is runing.");

        }

    }


    private void updateKolGrade() throws Exception{
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        Date date = format.parse("2023-02-01");
        Date date = new Date();
        if (isFirstDayOfMonth(date)){
            log.info("Is the first day of the month,date:{}", format.format(date));
            String month = getMonth();
            List<TKolPartner> tKolPartners = getAllTKolPartner();
            List<TKolRuleDetail> kolRules = getKolRules();
            HashMap<String, TKolRptMonth> hmKolRptMonth = getKolRptMonth(month);
            HashMap<String, TKolRuleDetail> hmGradeNameKolRules = new HashMap<String, TKolRuleDetail>();
            HashMap<String, TKolRuleDetail> hmIdKolRules = new HashMap<String, TKolRuleDetail>();
            int maxId=0;
            for (TKolRuleDetail  tKolRuleDetail : kolRules) {
                int currentId = Integer.parseInt(tKolRuleDetail.id);
                if (currentId > maxId){
                    maxId =  currentId;
                }
                hmGradeNameKolRules.put(tKolRuleDetail.grade_name, tKolRuleDetail);
                hmIdKolRules.put(tKolRuleDetail.id,tKolRuleDetail);
            }
            List<TKolPartner> update = new ArrayList<>();
            String time = getTime();
            for (TKolPartner tKolPartner : tKolPartners){
                String grade_name = tKolPartner.grade_name;
                if (hmGradeNameKolRules.containsKey(grade_name)){  
                    TKolRuleDetail ruleDetail = hmGradeNameKolRules.get(grade_name);
                    TKolRptMonth tKolRptMonth = hmKolRptMonth.get(tKolPartner.user_id);
                    if (ruleDetail != null) {
                        int currentId = Integer.parseInt(ruleDetail.id);
                        if (tKolRptMonth != null) {
                            if(kolRules != null){
                                boolean status = true;
                                if (currentId == maxId){
                                    status = false;
                                }else {
                                    for (int i=kolRules.size()-1; i>= 0; i--){
                                        TKolRuleDetail nextKolRuleDetail = kolRules.get(i);
                                        int nextId = Integer.parseInt(nextKolRuleDetail.id);
                                        if (!nextKolRuleDetail.grade_name.equals(grade_name) && nextId > currentId) {
                                            if (tKolRptMonth.total_inv_tran_num_mon.compareTo(new BigDecimal(nextKolRuleDetail.total_inv_tran_num_mon)) >= 0 && tKolRptMonth.total_inv_tran_vol_mon.compareTo(new BigDecimal(nextKolRuleDetail.total_inv_tran_vol_mon)) >= 0) {
                                                status = false;
                                                log.info("user_id:[{}] tKolRptMonth total_inv_tran_num_mon[{}],total_inv_tran_vol_mon:[{}] tKolRuleDetail id:[{}] grade_name:[{}] total_inv_tran_num_mon:[{}] total_inv_tran_vol_mon:[{}]", tKolPartner.user_id, tKolRptMonth.total_inv_tran_num_mon.toPlainString(), tKolRptMonth.total_inv_tran_vol_mon.toPlainString(), nextKolRuleDetail.id, nextKolRuleDetail.grade_name, nextKolRuleDetail.total_inv_tran_num_mon, nextKolRuleDetail.total_inv_tran_vol_mon);
                                                tKolPartner.grade_name = nextKolRuleDetail.grade_name;
                                                tKolPartner.direct_cus_ret_serv = new BigDecimal(nextKolRuleDetail.direct_cus_ret_serv);
                                                tKolPartner.second_com_prop = new BigDecimal(nextKolRuleDetail.second_com_prop);
                                                tKolPartner.close_by = "BatchSvr";
                                                tKolPartner.update_time = time;
                                                update.add(tKolPartner);
                                            }
                                        }
                                    }
                                }
                                if (status){
                                    for (TKolRuleDetail previousKolRuleDetail : kolRules) {
                                        int previousId = Integer.parseInt(previousKolRuleDetail.id);
                                        if (!previousKolRuleDetail.grade_name.equals(grade_name) &&  previousId < currentId) {
                                            if (tKolRptMonth.total_inv_tran_num_mon.compareTo(new BigDecimal(previousKolRuleDetail.total_inv_tran_num_mon)) < 0 && tKolRptMonth.total_inv_tran_vol_mon.compareTo(new BigDecimal(previousKolRuleDetail.total_inv_tran_vol_mon)) < 0) {
                                                log.info("user_id:[{}] tKolRptMonth total_inv_tran_num_mon[{}],total_inv_tran_vol_mon:[{}] tKolRuleDetail id:[{}] grade_name:[{}] total_inv_tran_num_mon:[{}] total_inv_tran_vol_mon:[{}]", tKolPartner.user_id, tKolRptMonth.total_inv_tran_num_mon.toPlainString(), tKolRptMonth.total_inv_tran_vol_mon.toPlainString(), previousKolRuleDetail.id, previousKolRuleDetail.grade_name, previousKolRuleDetail.total_inv_tran_num_mon, previousKolRuleDetail.total_inv_tran_vol_mon);
                                                tKolPartner.grade_name = previousKolRuleDetail.grade_name;
                                                tKolPartner.direct_cus_ret_serv = new BigDecimal(previousKolRuleDetail.direct_cus_ret_serv);
                                                tKolPartner.second_com_prop = new BigDecimal(previousKolRuleDetail.second_com_prop);
                                                tKolPartner.close_by = "BatchSvr";
                                                tKolPartner.update_time = time;
                                                update.add(tKolPartner);
                                            }
                                        }
                                    }
                                }
                            }

                        }
                    }
                }
            }
            if (update.size() >0) {
                updateKolPartnerGrade(update);
            }
        }else {
            log.info("Not the first day of the month,date:{}", format.format(date));
        }
    }

    public boolean isFirstDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + 1);
        int days = calendar.get(Calendar.DAY_OF_MONTH);
        return  days == 2;
    }

    public static String getMonth() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, -1);
        String date = format.format(cal.getTime());
        return date;
    }

    public static String getTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = format.format(new Date());
        return date;
    }

    private List<TKolPartner> getAllTKolPartner() throws Exception {
        String sql = "select * from dc_kol_partner where status='1'";
        List<TKolPartner> list = DBUtils.queryListThrowsException(sql, new Object[] {}, TKolPartner.class);

        log.info("getAllTKolPartner size:{}", list.size());
        return list;
    }


    private List<TKolRuleDetail> getKolRules() throws Exception {
        String sql = "select * from dc_kol_rule_detail order by id asc";
        List<TKolRuleDetail> list = DBUtils.queryListThrowsException(sql, new Object[] {}, TKolRuleDetail.class);
        log.info("getKolRules size:{}", list.size());
        return list;
    }

    private HashMap<String, TKolRptMonth> getKolRptMonth(String month) throws Exception {
        HashMap<String, TKolRptMonth> hm = new HashMap<String, TKolRptMonth>();
        String sql = String.format("select * from dc_kol_rpt_month where month='%s'", month);
        List<TKolRptMonth> list = DBUtils.queryListThrowsException(sql, new Object[] {}, TKolRptMonth.class);
        for (TKolRptMonth tKolRptMonth : list) {
            hm.put(tKolRptMonth.user_id, tKolRptMonth);
        }
        log.info("getKolRptMonth list size:{}", list.size());
        log.info("getKolRptMonth map size:{}", hm.size());
        return hm;
    }


    private void updateKolPartnerGrade (List<TKolPartner> update) throws Exception {
        String sql = "update dc_kol_partner set remark = ?,grade_name=?,direct_cus_ret_serv=?,second_com_prop=?,update_time=?,close_by=? where id=?";
        List<Object[]> args = new ArrayList<>();
        for (TKolPartner tKolPartner : update){
            Object[] o = new Object[7];
            o[0]="BathSvr upgrade";
            o[1]=tKolPartner.grade_name;
            o[2]= tKolPartner.direct_cus_ret_serv;
            o[3] = tKolPartner.second_com_prop;
            o[4]=tKolPartner.update_time;
            o[5] = tKolPartner.close_by;
            o[6] = tKolPartner.id;
            args.add(o);
        }
        DBUtils.updateList(sql,args);
    }
}
