package com.app.dc.batchsvr;

import com.app.common.db.DBUtils;
import com.app.dc.service.day.DepositCheckTask;
import com.app.dc.util.RestTemplateConfig;
import com.app.dc.utils.DateUtil;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

/**
 * @Description
 * @Author
 * @Date
 **/
public class TestDeposit {

    @Before
    public void init() {

        BasicConfigurator.configure();
        PropertyConfigurator.configure("./config/log4j.ini");


    }

    @Test
    public void TestUTC() {

        long utcTime = System.currentTimeMillis()/1000;

        System.out.println("utcTime:" + utcTime);

        long dateTime = new Date().getTime()/1000;

        System.out.println("dateTime:" + dateTime);
    }


    @Test
    public void TestSearchDeposit() {
        Date date = DateUtil.addMinutes(new Date(), Integer.valueOf(-120));
        String comDateFormat = DateUtil.YYYY_MM_DD + " " + DateUtil.HH_MM_SS;

        DepositCheckTask depositCheckTask = new DepositCheckTask();
        depositCheckTask.setDepositInterval("60");
        depositCheckTask.setDepositURL("https://graphql-test.ffdex.net/graphql");

//        depositCheckTask.setDepositURL("https://graphql.ffdex.net/graphql");

        depositCheckTask.setCurDepositCheckTime(DateUtil.formatDate(date, comDateFormat));

        RestTemplateConfig restTemplateConfig = new RestTemplateConfig();
        restTemplateConfig.restTemplate();

        depositCheckTask.setRestTemplateConfig(restTemplateConfig);

        depositCheckTask.start();

    }

}
