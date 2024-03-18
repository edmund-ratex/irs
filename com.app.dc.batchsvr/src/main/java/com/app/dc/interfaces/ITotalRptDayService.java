package com.app.dc.interfaces;

import com.app.dc.entity.BatchResult;
import com.app.dc.entity.SearchParam;
import com.app.dc.entity.TTotalRptDay;

/**
 * @Description
 * @Author
 * @Date
 **/
public interface ITotalRptDayService {

    void deleteByDate(String tradeDate);

    BatchResult selectTotalRpt(SearchParam searchParam);

    void insert(TTotalRptDay totalRptDay);

}
