package com.app.dc.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;


public class TKolRptDay {

	@JSONField(name = "id",serialize=true)
	public String id = "";
	@JSONField(name = "trade_date",serialize=true)
	public String trade_date;
	@JSONField(name = "kol_user_id",serialize=true)
	public String kol_user_id;
	@JSONField(name = "user_id",serialize=true)
	public String user_id;
	@JSONField(name = "first_login_time",serialize=true)
	public String first_login_time;
	@JSONField(name = "kol_time",serialize=true)
	public String kol_time;
	@JSONField(name = "first_deposit_time",serialize=true)
	public String first_deposit_time;
	@JSONField(name = "acc_deposit_amount",serialize=true)
	public BigDecimal acc_deposit_amount = new BigDecimal(0.00000000);
	@JSONField(name = "acc_withdraw_amount",serialize=true)
	public BigDecimal acc_withdraw_amount = new BigDecimal(0.00000000);
	@JSONField(name = "acc_transact_vol",serialize=true)
	public BigDecimal acc_transact_vol = new BigDecimal(0.00000000);
	@JSONField(name = "acc_transact_pl",serialize=true)
	public BigDecimal acc_transact_pl = new BigDecimal(0.00000000);
	@JSONField(name = "acc_transact_fee",serialize=true)
	public BigDecimal acc_transact_fee = new BigDecimal(0.00000000);
	@JSONField(name = "taker_volume",serialize=true)
	public BigDecimal taker_volume = new BigDecimal(0.00000000);
	@JSONField(name = "maker_volume",serialize=true)
	public BigDecimal maker_volume = new BigDecimal(0.00000000);
	@JSONField(name = "acc_com_reb",serialize=true)
	public BigDecimal acc_com_reb = new BigDecimal(0.00000000);
	@JSONField(name = "create_time",serialize=true)
	public String create_time;
	@JSONField(name = "update_time",serialize=true)
	public String update_time;
	@JSONField(name = "invite_number",serialize=true)
	public int invite_number = 0;
	@JSONField(name = "type",serialize=true)
	public String type;
	@JSONField(name = "invite_number_cash_in",serialize=true)
	public int invite_number_cash_in = 0;
	@JSONField(name = "invite_number_trade",serialize=true)
	public int invite_number_trade = 0;
	@JSONField(name = "url",serialize=true)
	public String url;
}