package com.app.dc.interfaces;

import com.app.dc.entity.BatchResult;
import com.app.dc.entity.SearchParam;

public interface ICashLogService {

    BatchResult selectCashLogByCondition(SearchParam searchParam);

}
