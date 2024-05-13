package com.app.dc.service.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Slf4j
public class ActivityTotalAssetsJob implements Job {

    private static boolean runFlag = false;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("ActivityTotalAssetsJob execute");
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
            log.info("ActivityTotalAssetsJob Job success");
            runFlag = false;
        } else {
            log.warn("ActivityTotalAssetsJob job is runing.");

        }
    }

    public void checkTotalAssets()throws Exception {
        String date = getDate();
        ActivityIntegralUtils.checkTotalAssets(date,date);
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
