package com.app.dc.po;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;


@Data
public class TSymbol {

    @JSONField(name = "id",serialize=true)
    public int id = 0;//
    @JSONField(name = "symbol",serialize=true)
    public String symbol;
    @JSONField(name = "symbol_en_name",serialize=true)
    public String symbol_en_name;
    @JSONField(name = "symbol_cn_name",serialize=true)
    public String symbol_cn_name;
    @JSONField(name = "index_symbol",serialize=true)
    public String index_symbol;
    @JSONField(name = "mark_symbol",serialize=true)
    public String mark_symbol;
    @JSONField(name = "predicate_funding_symbol",serialize=true)
    public String predicate_funding_symbol;
    @JSONField(name = "funding_symbol",serialize=true)
    public String funding_symbol;
    @JSONField(name = "tick_size",serialize=true)
    public String tick_size;
    @JSONField(name = "qty_tick_size",serialize=true)
    public String qty_tick_size;
    @JSONField(name = "price_precision",serialize=true)
    public int price_precision;
    @JSONField(name = "max_order_qty",serialize=true)
    public String max_order_qty;
    @JSONField(name = "tip_order_qty",serialize=true)
    public String tip_order_qty;
    @JSONField(name = "activity",serialize=true)
    public String activity;
    @JSONField(name = "expiried",serialize=true)
    public String expiried;
    @JSONField(name = "ticker_root",serialize=true)
    public String ticker_root;
    @JSONField(name = "contract_size",serialize=true)
    public String contract_size;
    @JSONField(name = "initial_margin",serialize=true)
    public String initial_margin;
    @JSONField(name = "maint_margin",serialize=true)
    public String maint_margin;
    @JSONField(name = "funding_rate_precision",serialize=true)
    public int funding_rate_precision;
    @JSONField(name = "funding_interval",serialize=true)
    public int funding_interval;
    @JSONField(name = "predicted_rate",serialize=true)
    public String predicted_rate;
    @JSONField(name = "adl_enable",serialize=true)
    public String adl_enable;
    @JSONField(name = "risk_limit",serialize=true)
    public String risk_limit;
    @JSONField(name = "risk_step",serialize=true)
    public String risk_step;
    @JSONField(name = "max_price",serialize=true)
    public String max_price;
    @JSONField(name = "taker_commission",serialize=true)
    public String taker_commission;
    @JSONField(name = "maker_commission",serialize=true)
    public String maker_commission;
    @JSONField(name = "create_time",serialize=true)
    public String create_time;
    @JSONField(name = "update_time",serialize=true)
    public String update_time;
    @JSONField(name = "close_by",serialize=true)
    public String close_by;
    @JSONField(name = "volume_precision",serialize=true)
    public int volume_precision;
    @JSONField(name = "base_currency",serialize=true)
    public String base_currency;
    @JSONField(name = "quote_currency",serialize=true)
    public String quote_currency;
    @JSONField(name = "qty_precision",serialize=true)
    public int qty_precision;
    @JSONField(name = "value_precision",serialize=true)
    public int value_precision;
    @JSONField(name = "assets",serialize=true)
    public String assets;
}