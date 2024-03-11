package com.app.dc.interfaces;

import com.app.dc.entity.BatchResult;

/**
 * @Description
 * @Author
 * @Date
 **/
public interface IMarkPriceService {

    void insertFromCache(String tradeDate);

    void deleteMarketPriceByDate(String tradeDate);

    BatchResult getMarketPriceByCondition(String tradeDate);
}
