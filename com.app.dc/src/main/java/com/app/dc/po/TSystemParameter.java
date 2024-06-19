package com.app.dc.po;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;

  
public class TSystemParameter {

	 @JSONField(name = "param_key",serialize=true)
	 public String param_key = "";
	 @JSONField(name = "user_id",serialize=true)
	 public String user_id = "";
	 @JSONField(name = "param_value",serialize=true)
	 public String param_value;
	 @JSONField(name = "param_desc",serialize=true)
	 public String param_desc;
	 @JSONField(name = "create_time",serialize=true)
	 public String create_time;
	 @JSONField(name = "update_time",serialize=true)
	 public String update_time;
	}
