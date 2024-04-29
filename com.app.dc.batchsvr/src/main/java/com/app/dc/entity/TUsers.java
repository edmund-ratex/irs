package com.app.dc.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;


public class TUsers {

	 @JSONField(name = "chain_id",serialize=true)
	 public String chain_id;
	 @JSONField(name = "user_id",serialize=true)
	 public String user_id;
	 @JSONField(name = "user_name",serialize=true)
	 public String user_name = "";
	 @JSONField(name = "name",serialize=true)
	 public String name;
	 @JSONField(name = "password",serialize=true)
	 public String password;
	 @JSONField(name = "mail",serialize=true)
	 public String mail;
	 @JSONField(name = "user_type",serialize=true)
	 public String user_type;
	 @JSONField(name = "enable",serialize=true)
	 public String enable;
	 @JSONField(name = "remark",serialize=true)
	 public String remark;
	 @JSONField(name = "create_time",serialize=true)
	 public String create_time;
	 @JSONField(name = "update_time",serialize=true)
	 public String update_time;
	 @JSONField(name = "mac",serialize=true)
	 public String mac;
	 @JSONField(name = "mac1",serialize=true)
	 public String mac1;
	 @JSONField(name = "mac2",serialize=true)
	 public String mac2;
	 @JSONField(name = "inf1",serialize=true)
	 public String inf1;
	 @JSONField(name = "inf2",serialize=true)
	 public String inf2;
	 @JSONField(name = "inf3",serialize=true)
	 public String inf3;
	 @JSONField(name = "inf4",serialize=true)
	 public String inf4;
	 @JSONField(name = "inf5",serialize=true)
	 public String inf5;
	 @JSONField(name = "enable_trade",serialize=true)
	 public String enable_trade;
	 @JSONField(name = "enable_cash_in",serialize=true)
	 public String enable_cash_in;
	 @JSONField(name = "enable_cash_out",serialize=true)
	 public String enable_cash_out;
	 @JSONField(name = "referrer",serialize=true)
	 public String referrer;
	 @JSONField(name = "close_by",serialize=true)
	 public String close_by;
	 @JSONField(name = "fee_discount_ratio",serialize=true)
	 public BigDecimal fee_discount_ratio;
	}