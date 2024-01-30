package com.app.dc;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;
/**
 *
 *2023.09.12
 */
public class TAuditLog {

    @JSONField(name = "id",serialize=true)
    public String id = "";//ID
    @JSONField(name = "user_id",serialize=true)
    public String user_id;
    @JSONField(name = "type",serialize=true)
    public String type;
    @JSONField(name = "status",serialize=true)
    public String status;
    @JSONField(name = "ip",serialize=true)
    public String ip;
    @JSONField(name = "mac",serialize=true)
    public String mac;
    @JSONField(name = "terminal",serialize=true)
    public String terminal;
    @JSONField(name = "content",serialize=true)
    public String content;
    @JSONField(name = "create_time",serialize=true)
    public String create_time;
    @JSONField(name = "close_by",serialize=true)
    public String close_by;
}
