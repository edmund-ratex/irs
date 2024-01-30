package com.app.dc.batchsvr;

import com.app.common.db.DBUtils;
import com.app.dc.service.day.BatchUserDayTask;
import com.gateway.connector.tcp.client.GateWayApi;
import com.gateway.connector.tcp.client.IEventListener;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Description
 * @Author
 * @Date
 **/
public class TestBatchTask {

    private BatchUserDayTask batchUserDayTask = new BatchUserDayTask();

    @Before
    public void init() {

        BasicConfigurator.configure();
        PropertyConfigurator.configure("./config/log4j.ini");

        try {
            DBUtils.init("MYSQL0", "./config/DBPoolConfig.ini");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Test
    public void testDayBatch() {

//        batchUserDayTask.startTask("20230227");
//
//        try {
//            System.in.read();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        TestBatchTask testBatchTask = new TestBatchTask();
        try {
            boolean isMondays = testBatchTask.isSunday("2023-07-23");
            System.out.println(isMondays);
        } catch (ParseException e) {
            e.printStackTrace();
        }
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

}
