package com.app.dc.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;

public class TTotalRptDay {

    @JSONField(name = "id",serialize=true)
    public String id = "";
    @JSONField(name = "trade_date",serialize=true)
    public String trade_date;
    @JSONField(name = "volume",serialize=true)
    public BigDecimal volume = new BigDecimal(0);
    @JSONField(name = "total_volume",serialize=true)
    public BigDecimal total_volume = new BigDecimal(0);
    @JSONField(name = "fee",serialize=true)
    public BigDecimal fee = new BigDecimal(0);
    @JSONField(name = "total_fee",serialize=true)
    public BigDecimal total_fee = new BigDecimal(0);
    @JSONField(name = "users",serialize=true)
    public BigDecimal users = new BigDecimal(0);
    @JSONField(name = "total_users",serialize=true)
    public BigDecimal total_users = new BigDecimal(0);
    @JSONField(name = "create_time",serialize=true)
    public String create_time;
    @JSONField(name = "update_time",serialize=true)
    public String update_time;
    @JSONField(name = "close_by",serialize=true)
    public String close_by;
}
