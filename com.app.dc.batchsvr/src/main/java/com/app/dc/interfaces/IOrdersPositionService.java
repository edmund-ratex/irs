package com.app.dc.interfaces;

import com.app.dc.entity.BatchResult;
import com.app.dc.entity.SearchParam;

/**
 * @Description
 * @Author
 * @Date
 **/
public interface IOrdersPositionService {

    BatchResult selectOrderPostionDayByPage(SearchParam req);

    void insertFromOrderPostion(String tradeDate);

    void deleteOrderPostionByDate(String tradeDate);

}
