package com.app.dc.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;
 /**
   *
  *
  *2023.01.02
  */
public class TKolRpt {

	 @JSONField(name = "kol_user_id", serialize = true)
	 public String kol_user_id = "";
	 @JSONField(name = "acc_com_reb", serialize = true)
	 public BigDecimal acc_com_reb = new BigDecimal(0.00000000);
	 @JSONField(name = "acc_com_reb_out", serialize = true)
	 public BigDecimal acc_com_reb_out = new BigDecimal(0.00000000);
	 @JSONField(name = "acc_deposit_amount1", serialize = true)
	 public BigDecimal acc_deposit_amount1 = new BigDecimal(0.00000000);
	 @JSONField(name = "acc_withdraw_amount1", serialize = true)
	 public BigDecimal acc_withdraw_amount1 = new BigDecimal(0.00000000);
	 @JSONField(name = "acc_transact_vol1", serialize = true)
	 public BigDecimal acc_transact_vol1 = new BigDecimal(0.00000000);
	 @JSONField(name = "acc_transact_pl1", serialize = true)
	 public BigDecimal acc_transact_pl1 = new BigDecimal(0.00000000);
	 @JSONField(name = "acc_transact_fee1", serialize = true)
	 public BigDecimal acc_transact_fee1 = new BigDecimal(0.00000000);
	 @JSONField(name = "taker_volume1", serialize = true)
	 public BigDecimal taker_volume1 = new BigDecimal(0.00000000);
	 @JSONField(name = "maker_volume1", serialize = true)
	 public BigDecimal maker_volume1;
	 @JSONField(name = "acc_com_reb1", serialize = true)
	 public BigDecimal acc_com_reb1 = new BigDecimal(0.00000000);
	 @JSONField(name = "update_time", serialize = true)
	 public String update_time;
	 @JSONField(name = "invite_number1", serialize = true)
	 public int invite_number1 = 0;
	 @JSONField(name = "invite_number_cash_in1", serialize = true)
	 public int invite_number_cash_in1 = 0;
	 @JSONField(name = "invite_number_trade1", serialize = true)
	 public int invite_number_trade1 = 0;
	 @JSONField(name = "acc_deposit_amount2", serialize = true)
	 public BigDecimal acc_deposit_amount2;
	 @JSONField(name = "acc_withdraw_amount2", serialize = true)
	 public BigDecimal acc_withdraw_amount2 = new BigDecimal(0.00000000);
	 @JSONField(name = "acc_transact_vol2", serialize = true)
	 public BigDecimal acc_transact_vol2 = new BigDecimal(0.00000000);
	 @JSONField(name = "acc_transact_pl2", serialize = true)
	 public BigDecimal acc_transact_pl2 = new BigDecimal(0.00000000);
	 @JSONField(name = "acc_transact_fee2", serialize = true)
	 public BigDecimal acc_transact_fee2 = new BigDecimal(0.00000000);
	 @JSONField(name = "taker_volume2", serialize = true)
	 public BigDecimal taker_volume2 = new BigDecimal(0.00000000);
	 @JSONField(name = "maker_volume2", serialize = true)
	 public BigDecimal maker_volume2 = new BigDecimal(0.00000000);
	 @JSONField(name = "acc_com_reb2", serialize = true)
	 public BigDecimal acc_com_reb2 = new BigDecimal(0.00000000);
	 @JSONField(name = "create_time2", serialize = true)
	 public String create_time2;
	 @JSONField(name = "update_time2", serialize = true)
	 public String update_time2;
	 @JSONField(name = "invite_number2", serialize = true)
	 public int invite_number2 = 0;
	 @JSONField(name = "invite_number_cash_in2", serialize = true)
	 public int invite_number_cash_in2 = 0;
	 @JSONField(name = "invite_number_trade2", serialize = true)
	 public int invite_number_trade2 = 0;
	 @JSONField(name = "create_time", serialize = true)
	 public String create_time;
	 @JSONField(name = "close_by", serialize = true)
	 public String close_by;
	 @JSONField(name = "acc_click_number", serialize = true)
	 public int acc_click_number = 0;
 }