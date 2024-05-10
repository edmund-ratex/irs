package com.app.dc.service.job;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.app.common.db.DBUtils;
import com.app.dc.entity.*;
import com.app.dc.util.Consts;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
public class ActivityTotalAssets12Job implements Job {
    private static boolean runFlag = false;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("ActivityTotalAssets12Job execute");
        try {
            Job();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    public synchronized void Job() throws Exception {
        if (!runFlag) {
            runFlag = true;
            checkTotalAssets();
            log.info("ActivityTotalAssets12Job Job success");
            runFlag = false;
        } else {
            log.warn("ActivityTotalAssets12Job job is runing.");

        }
    }


    public void checkTotalAssets()throws Exception {
        String todayDate = getTodayDate();
        String date = getDate();
        ActivityIntegralUtils.checkTotalAssets(todayDate,date);
    }

    public static String getTodayDate() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String date;
        date = format.format(new Date());
        return date;
    }

    public static String getDate() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String date;
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, -1);
        date = format.format(cal.getTime());
        return date;
    }



}
