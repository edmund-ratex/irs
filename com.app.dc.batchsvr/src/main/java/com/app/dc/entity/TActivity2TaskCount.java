package com.app.dc.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;
/**
 *
 *
 *2023.08.05:
 */
public class TActivity2TaskCount {
    @JSONField(name = "user_id",serialize=true)
    public String user_id = "";//
    @JSONField(name = "week_balance_count",serialize=true)
    public int week_balance_count = 0;//
    @JSONField(name = "account_balance_count1",serialize=true)
    public int account_balance_count1 = 0;//
    @JSONField(name = "account_balance_count2",serialize=true)
    public int account_balance_count2 = 0;//
}
