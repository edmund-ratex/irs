package com.app.dc.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;


/**
 *
 *
 *2023.04.08
 */
@Data
public class TUsersRptDay {

	@JSONField(name = "id",serialize=true)
	public String id = "";
	@JSONField(name = "user_id",serialize=true)
	public String user_id;
	@JSONField(name = "trade_date",serialize=true)
	public String trade_date;
	@JSONField(name = "first_login_time",serialize=true)
	public String first_login_time;
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
	@JSONField(name = "invite_number",serialize=true)
	public int invite_number = 0;
	@JSONField(name = "total_pnl",serialize=true)
	public BigDecimal total_pnl = new BigDecimal(0.00000000);
	@JSONField(name = "day_pnl",serialize=true)
	public BigDecimal day_pnl = new BigDecimal(0.00000000);
	@JSONField(name = "total_roi",serialize=true)
	public BigDecimal total_roi = new BigDecimal(0.00000000);
	@JSONField(name = "create_time",serialize=true)
	public String create_time;
	@JSONField(name = "update_time",serialize=true)
	public String update_time;
	@JSONField(name = "acc_transact_vol2",serialize=true)
	public BigDecimal acc_transact_vol2 = new BigDecimal(0.00000000);
	@JSONField(name = "invite_number2",serialize=true)
	public int invite_number2 = 0;
	@JSONField(name = "activity1_invite_number1",serialize=true)
	public int activity1_invite_number1 = 0;
	@JSONField(name = "activity1_invite_number2",serialize=true)
	public int activity1_invite_number2 = 0;
	@JSONField(name = "activity1_integral",serialize=true)
	public int activity1_integral = 0;
	@JSONField(name = "integral",serialize=true)
	public int integral = 0;
	@JSONField(name = "activity1_prize",serialize=true)
	public String activity1_prize = "0";
	@JSONField(name = "activity_prize",serialize=true)
	public String activity_prize = "0";
	@JSONField(name = "acc_pl",serialize=true)
	public BigDecimal acc_pl = new BigDecimal(0.00000000);
	@JSONField(name = "acc_pl_ratio",serialize=true)
	public BigDecimal acc_pl_ratio = new BigDecimal(0.00000000);
	@JSONField(name = "total_assets",serialize=true)
	public BigDecimal total_assets = new BigDecimal(0.00000000);
	@JSONField(name = "daily_acc_pl",serialize=true)
	public BigDecimal daily_acc_pl = new BigDecimal(0.00000000);
	@JSONField(name = "activity2_integral",serialize=true)
	public int activity2_integral = 0;
	@JSONField(name = "activity2_prize",serialize=true)
	public String activity2_prize = "0";
}