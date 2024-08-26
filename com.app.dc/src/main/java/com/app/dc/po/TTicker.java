package com.app.dc.po;

import lombok.Data;

/**
 * @Description
 * @Author
 * @Date
 **/
@Data
public class TTicker {

    private String symbol;
    private String last_price;
    private String bid_price;
    private String ask_price;
    private String open_price;
    private String high_price;
    private String low_price;
    private String volume;
    private String turnover;
    private String open_interest;
    private String index_price;
    private String mark_price;
    private String predict_funding_rate;
    private String funding_rate;

    private long timestamp;

}
