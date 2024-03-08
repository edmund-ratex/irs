package com.app.dc.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DataUtils {

    /**
     * Get all dates within a certain period of time
     * @param startDateStr yyyy-MM-dd
     * @param endDateStr yyyy-MM-dd
     * @return yyyy-MM-dd
     */
    public static List<String> findDates(String startDateStr, String endDateStr) throws ParseException {
        Date startDate;
        Date endDate;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        startDate = sdf.parse(startDateStr);
        endDate = sdf.parse(endDateStr);
        Calendar cStart = Calendar.getInstance();
        cStart.setTime(startDate);

        List dateList = new ArrayList();
        dateList.add(startDateStr);
        while (endDate.after(cStart.getTime())) {
            cStart.add(Calendar.DAY_OF_YEAR, 1);
            if(endDate.compareTo(cStart.getTime()) < 0) {
                break;
            }
            dateList.add(sdf.format(cStart.getTime()));
        }
        return dateList;
    }
}
