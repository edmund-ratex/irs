package com.app.dc.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;


public class TOrdersExecorders {

	@JSONField(name = "exec_id",serialize=true)
	public String exec_id = "";
	@JSONField(name = "order_id",serialize=true)
	public String order_id = "";
	@JSONField(name = "user_id",serialize=true)
	public String user_id = "";
	@JSONField(name = "account_id",serialize=true)
	public BigDecimal account_id;
	@JSONField(name = "currency_id",serialize=true)
	public int currency_id;
	@JSONField(name = "currency",serialize=true)
	public String currency = "";
	@JSONField(name = "symbol_id",serialize=true)
	public int symbol_id;
	@JSONField(name = "symbol",serialize=true)
	public String symbol;
	@JSONField(name = "exec_type",serialize=true)
	public String exec_type;
	@JSONField(name = "side",serialize=true)
	public String side;
	@JSONField(name = "exec_price",serialize=true)
	public BigDecimal exec_price;
	@JSONField(name = "exec_qty",serialize=true)
	public BigDecimal exec_qty;
	@JSONField(name = "exec_value",serialize=true)
	public BigDecimal exec_value;
	@JSONField(name = "closed_size",serialize=true)
	public BigDecimal closed_size;
	@JSONField(name = "closed_pnl",serialize=true)
	public BigDecimal closed_pnl;
	@JSONField(name = "fee_rate",serialize=true)
	public BigDecimal fee_rate;
	@JSONField(name = "exec_fee",serialize=true)
	public BigDecimal exec_fee;
	@JSONField(name = "update_time",serialize=true)
	public String update_time;
	@JSONField(name = "close_by",serialize=true)
	public String close_by;
	@JSONField(name = "create_time",serialize=true)
	public String create_time;
	@JSONField(name = "vs_user_id",serialize=true)
	public String vs_user_id;
	@JSONField(name = "external_create_time",serialize=true)
	public String external_create_time;
	@JSONField(name = "trade_type",serialize=true)
	public String trade_type;
}