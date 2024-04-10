package com.app.dc.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;
 /**
   *
  *
  *2022.12.25: (v1.0.0.1) <br/>
  */
public class TCashLog {

	 @JSONField(name = "id", serialize = true)
	 public String id = "";
	 @JSONField(name = "user_id", serialize = true)
	 public String user_id = "";
	 @JSONField(name = "account_id", serialize = true)
	 public String account_id = "";
	 @JSONField(name = "address", serialize = true)
	 public String address;
	 @JSONField(name = "txid", serialize = true)
	 public String txid = "";
	 @JSONField(name = "confirm_num", serialize = true)
	 public int confirm_num;
	 @JSONField(name = "currency", serialize = true)
	 public int currency;
	 @JSONField(name = "amount", serialize = true)
	 public BigDecimal amount;
	 @JSONField(name = "status", serialize = true)
	 public int status;
	 @JSONField(name = "side", serialize = true)
	 public int side;
	 @JSONField(name = "nonce", serialize = true)
	 public int nonce;
	 @JSONField(name = "fee_type", serialize = true)
	 public String fee_type;
	 @JSONField(name = "fee_value", serialize = true)
	 public String fee_value;
	 @JSONField(name = "fee_amount", serialize = true)
	 public BigDecimal fee_amount;
	 @JSONField(name = "msg", serialize = true)
	 public String msg;
	 @JSONField(name = "remark", serialize = true)
	 public String remark;
	 @JSONField(name = "create_time", serialize = true)
	 public String create_time;
	 @JSONField(name = "update_time", serialize = true)
	 public String update_time;
	 @JSONField(name = "close_by", serialize = true)
	 public String close_by;
	 @JSONField(name = "chain_id", serialize = true)
	 public String chain_id;
	 @JSONField(name = "verify_contract", serialize = true)
	 public String verify_contract;
	 @JSONField(name = "deposit_address", serialize = true)
	 public String deposit_address;
 }