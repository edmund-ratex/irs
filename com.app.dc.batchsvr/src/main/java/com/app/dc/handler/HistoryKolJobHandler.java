package com.app.dc.handler;

import com.alibaba.fastjson.JSON;
import com.app.common.utils.JsonUtils;
import com.app.dc.service.job.KolJob;
import com.app.dc.util.Consts;
import com.app.dc.util.DataUtils;
import com.app.dc.utils.CodeMsgEnum;
import com.gw.common.utils.ContentHandler;
import com.gw.common.utils.Message;
import org.apache.commons.lang3.StringUtils;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service("HistoryKolJob")
public class HistoryKolJobHandler extends ContentHandler {

    public Map<String, Object> handle(String topic, Message message, String content, Map<String, Object> map,boolean fromList) {
        logger.info("HistoryKolJobHandler content:{}", content);
        String sid = message.getSignalID();
        logger.debug("sid:{},{}", sid, content);
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            Map map1 = JsonUtils.Deserialize(content, HashMap.class);
            String startDate = map1.get("StartDate").toString();
            String endDate = map1.get("EndDate").toString();
            if (StringUtils.isEmpty(endDate)) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.DAY_OF_YEAR, -1);
                endDate = format.format(cal.getTime());
            }
            List<String> list = DataUtils.findDates(startDate,endDate);
            logger.info("Job Date:{}",JSON.toJSONString(list));
            Runnable target3 = () -> {
                KolJob kolJob = new KolJob();
                try {
                    kolJob.runFlag = false;
                    for (String date : list){
                        kolJob.kolJob(date, Consts.Kol_Batch_History);
                        logger.info("kolJob date:{} success",date);
                    }

                }  catch (Exception e) {
                    logger.error(e.getMessage());
                }
            };
            new Thread(target3).start();

            result.put(Consts.Code, CodeMsgEnum.NO_ERROR.getCode());
            result.put(Consts.Msg, CodeMsgEnum.NO_ERROR.name());
        } catch (Exception e) {
            logger.error("HistoryKolJobHandler error:{}",e);
            result.put(Consts.Code, CodeMsgEnum.UNKNOWN_ERROR.getCode());
            result.put(Consts.Msg, e.getMessage());
        }

        logger.info("result:{}", result);
        return result;
    }
}
