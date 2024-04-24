package com.app.dc.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;


public class TNftOwners {

    @JSONField(name = "id",serialize=true)
    public Integer id = 0;//
    @JSONField(name = "owner",serialize=true)
    public String owner = "";//
    @JSONField(name = "update_time_str",serialize=true)
    public String update_time_str = "";//
}
