package com.app.dc.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.math.BigDecimal;

public class TDepositLog {

    @JSONField(name = "id",serialize=true)
    public int id;
    @JSONField(name = "user_id",serialize=true)
    public String user_id = "";
    @JSONField(name = "account_id",serialize=true)
    public String account_id = "";
    @JSONField(name = "address",serialize=true)
    public String address;
    @JSONField(name = "txHash",serialize=true)
    public String txHash = "";

    @JSONField(name = "block_number",serialize=true)
    public int block_number;
    @JSONField(name = "block_time",serialize=true)
    public int block_time;
    @JSONField(name = "amount",serialize=true)
    public BigDecimal amount;

    @JSONField(name = "token",serialize=true)
    public String token = "";

    @JSONField(name = "source",serialize=true)
    public String source = "";

    @JSONField(name = "status",serialize=true)
    public String status = "";

    @JSONField(name = "tx_url",serialize=true)
    public String tx_url = "";
    @JSONField(name = "create_time",serialize=true)
    public String create_time = "";
    @JSONField(name = "update_time",serialize=true)
    public String update_time = "";
    @JSONField(name = "close_by",serialize=true)
    public String close_by = "";
    @JSONField(name = "chain_id",serialize=true)
    public String chain_id = "";

    @JSONField(name = "verify_contract",serialize=true)
    public String verify_contract = "";

}
