package com.app.dc.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;

public class TKolRptDetail {

		@JSONField(name = "id",serialize=true)
		public String id = "";	
		@JSONField(name = "kol_user_id",serialize=true)
		public String kol_user_id;
		@JSONField(name = "trade_date",serialize=true)
		public String trade_date;
		@JSONField(name = "user_id",serialize=true)
		public String user_id;	
		@JSONField(name = "type",serialize=true)
		public String type;	
		@JSONField(name = "volume",serialize=true)
		public BigDecimal volume;
		@JSONField(name = "percent",serialize=true)
		public BigDecimal percent;	
		@JSONField(name = "amount",serialize=true)
		public BigDecimal amount;		
		@JSONField(name = "create_time",serialize=true)
		public String create_time;	
		@JSONField(name = "status",serialize=true)
		public String status;		
		@JSONField(name = "update_time",serialize=true)
		public String update_time;	
	}