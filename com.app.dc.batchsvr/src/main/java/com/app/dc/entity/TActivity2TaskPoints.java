package com.app.dc.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 *dc_activity2_task_points
 *
 *2023.07.26
 */
@Data
public class TActivity2TaskPoints {

    @JSONField(name = "user_id",serialize=true)
    public String user_id = "";
    @JSONField(name = "week_deposit",serialize=true)
    public BigDecimal week_deposit = new BigDecimal(0.00000000);
    @JSONField(name = "week_sum_deposit",serialize=true)
    public BigDecimal week_sum_deposit = new BigDecimal(0.00000000);
    @JSONField(name = "week_in_cash",serialize=true)
    public BigDecimal week_in_cash = new BigDecimal(0.00000000);
    @JSONField(name = "week_deposit_integral",serialize=true)
    public int week_deposit_integral = 0;
    @JSONField(name = "week_balance",serialize=true)
    public BigDecimal week_balance = new BigDecimal(0.00000000);
    @JSONField(name = "week_balance_integral",serialize=true)
    public int week_balance_integral = 0;
    @JSONField(name = "week_trading_volume",serialize=true)
    public BigDecimal week_trading_volume = new BigDecimal(0.00000000);
    @JSONField(name = "week_sum_trading_volume",serialize=true)
    public BigDecimal week_sum_trading_volume = new BigDecimal(0.00000000);
    @JSONField(name = "week_trading_volume_integral",serialize=true)
    public int week_trading_volume_integral = 0;
    @JSONField(name = "week_invitation_state",serialize=true)
    public int week_invitation_state = 0;
    @JSONField(name = "week_invitation_integral",serialize=true)
    public int week_invitation_integral = 0;
    @JSONField(name = "week_integral",serialize=true)
    public int week_integral = 0;
    @JSONField(name = "linea_deposit",serialize=true)
    public BigDecimal linea_deposit = new BigDecimal(0.00000000);
    @JSONField(name = "linea_sum_deposit",serialize=true)
    public BigDecimal linea_sum_deposit = new BigDecimal(0.00000000);
    @JSONField(name = "linea_deposit_integral",serialize=true)
    public int linea_deposit_integral = 0;
    @JSONField(name = "og_gem_nft_integral",serialize=true)
    public int og_gem_nft_integral = 0;
    @JSONField(name = "deposit",serialize=true)
    public BigDecimal deposit = new BigDecimal(0.00000000);
    @JSONField(name = "sum_deposit",serialize=true)
    public BigDecimal sum_deposit = new BigDecimal(0.00000000);
    @JSONField(name = "sum_in_cash",serialize=true)
    public BigDecimal sum_in_cash = new BigDecimal(0.00000000);
    @JSONField(name = "deposit_integral1",serialize=true)
    public int deposit_integral1 = 0;
    @JSONField(name = "deposit_integral2",serialize=true)
    public int deposit_integral2 = 0;
    @JSONField(name = "account_total_balance",serialize=true)
    public BigDecimal account_total_balance = new BigDecimal(0.00000000);
    @JSONField(name = "account_total_balance_integral1",serialize=true)
    public int account_total_balance_integral1 = 0;
    @JSONField(name = "account_total_balance_integral2",serialize=true)
    public int account_total_balance_integral2 = 0;
    @JSONField(name = "trading_volume",serialize=true)
    public BigDecimal trading_volume = new BigDecimal(0.00000000);
    @JSONField(name = "sum_trading_volume",serialize=true)
    public BigDecimal sum_trading_volume = new BigDecimal(0.00000000);
    @JSONField(name = "trading_volume_integral1",serialize=true)
    public int trading_volume_integral1 = 0;
    @JSONField(name = "trading_volume_integral2",serialize=true)
    public int trading_volume_integral2 = 0;
    @JSONField(name = "invite_state",serialize=true)
    public int invite_state = 0;
    @JSONField(name = "invite_integral",serialize=true)
    public int invite_integral = 0;
    @JSONField(name = "share_count",serialize=true)
    public int share_count = 0;
    @JSONField(name = "share_integral",serialize=true)
    public int share_integral = 0;
}
