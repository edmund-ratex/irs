package com.app.dc.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Description
 * @Author
 * @Date
 **/
@Data
public class SearchParam {

    private String trade_date;
    private String user_id;

    private int page_size = 2000;
    private int page_num;

    private String sort_field;//sort_field
    private String sort_type;//asc, desc

    private String create_time;
    private String update_time;

    private String start_cash_time;
    private String end_cash_time;
    private String cash_side;
}
