package com.app.dc.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.app.common.utils.Consts;
import com.app.common.utils.JsonUtils;
import com.app.dc.entity.BatchResult;
import com.app.dc.interfaces.IIntelligentDataInsightService;
import com.app.dc.utils.CodeMsgEnum;
import com.gw.common.utils.ContentHandler;
import com.gw.common.utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("IntelligentDataInsight")
public class IntelligentDataInsightHandler extends ContentHandler {

    @Value("${intelligent.data.insight.key:F53AEC95BE17F3AB20A127770A1ECC11}")
    private String intelligentDataKey;

    @Autowired
    private IIntelligentDataInsightService iIntelligentDataInsightService;

    @Override
    public Map<String, Object> handle(String topic, Message message, String content, Map<String, Object> map, boolean fromList) {
        Map<String, Object> result = new HashMap<String, Object>();
        logger.info("IntelligentDataInsightHandler content:{}", content);
        try {
            String sid = message.getSignalID();
            logger.debug("sid:{},{}", sid, content);
            Map map1 = JsonUtils.Deserialize(content, HashMap.class);
            String param1 = map1.get("param1").toString();
            String param2 = map1.get("param2").toString();
            if (intelligentDataKey.equals(param1)) {
                BatchResult batchResult = iIntelligentDataInsightService.selectDataByTable(param2);
                result.put(Consts.Code, batchResult.code);
                result.put(Consts.DATA, batchResult.data);
                result.put(Consts.Msg, batchResult.msg);
            }else {
                result.put(Consts.Code, CodeMsgEnum.PARAMETER_ERROR.getCode());
                result.put(Consts.Msg, CodeMsgEnum.PARAMETER_ERROR.name());
            }
        }catch (Exception e){
            logger.error("IntelligentDataInsightHandler process error", e);
            result.put(Consts.Code, CodeMsgEnum.UNKNOWN_ERROR.getCode());
            result.put(Consts.Msg, e.getMessage());
        }
        return result;
    }

}
