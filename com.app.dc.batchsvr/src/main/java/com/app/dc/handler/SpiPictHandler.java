package com.app.dc.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.gw.common.utils.ContentHandler;
import com.gw.common.utils.Message;

@Service("spiPict")
public class SpiPictHandler extends ContentHandler {
	public Map<String, Object> handle(String topic, Message message, String content, Map<String, Object> map,
			boolean fromList) {
		String sid = message.getSignalID();
		logger.debug("sid:{},{}", sid, content);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("Code", "0000");
		result.put("Msg", "Success");
		result.put("datas", "Demo1Handler");
		logger.info("result:{}", result);
		return result;
	}

}
