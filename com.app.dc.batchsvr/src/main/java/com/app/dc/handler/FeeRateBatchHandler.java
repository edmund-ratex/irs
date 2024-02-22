package com.app.dc.handler;

import com.app.common.utils.Consts;
import com.app.common.utils.JsonUtils;
import com.app.dc.entity.SearchParam;
import com.app.dc.service.day.FeeRateDayTask;
import com.app.dc.utils.CodeMsgEnum;
import com.gw.common.utils.ContentHandler;
import com.gw.common.utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author
 * @Date
 **/
@RestController
@RequestMapping(path = "/batch")
@Service("feeRateBatch")
public class FeeRateBatchHandler extends ContentHandler {

    @Autowired
    private FeeRateDayTask feeRateDayTask;

    @Override
    public Map<String, Object> handle(String topic, Message message, String content, Map<String, Object> map,
                                      boolean fromList) {
        logger.info("FeeRateBatchHandler content:{}", content);
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            String sid = message.getSignalID();
            logger.debug("sid:{},{}", sid, content);

            SearchParam searchParam = JsonUtils.Deserialize(content, SearchParam.class);

            feeRateDayTask.start(searchParam.getTrade_date());


            HashMap<String, Object> dataMap = new HashMap<>();

            result.put(Consts.Code, CodeMsgEnum.NO_ERROR.getCode());
            result.put(Consts.Msg, CodeMsgEnum.NO_ERROR.name());
            result.put("data", dataMap);
        } catch (Exception e) {
            logger.error("FeeRateBatchHandler process error", e);
            result.put(Consts.Code, CodeMsgEnum.UNKNOWN_ERROR.getCode());
            result.put(Consts.Msg, e.getMessage());
        }
        return result;
    }

    @PostMapping(path = "/feeRateBatch")
    public void executeBatch(@RequestBody SearchParam searchParam, HttpServletRequest httpServletRequest) {
        Map<String, Object> result = new HashMap<String, Object>();

        feeRateDayTask.start(searchParam.getTrade_date());

        HashMap<String, Object> dataMap = new HashMap<>();

        result.put(Consts.Code, CodeMsgEnum.NO_ERROR.getCode());
        result.put(Consts.Msg, CodeMsgEnum.NO_ERROR.name());
        result.put("data", dataMap);
    }

}
