package com.app.dc.interfaces;

import com.app.dc.entity.BatchResult;

/**
 * @Description
 * @Author
 * @Date
 **/
public interface IPostingService {

    BatchResult countFeeRate(String tradeDate);

}
