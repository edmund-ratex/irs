package com.app.dc.interfaces;

import com.app.dc.entity.BatchResult;
import com.app.dc.entity.SearchParam;

/**
 * @Description
 * @Author
 * @Date
 **/
public interface IOrderExecService {

    BatchResult selectOrderExecByCondition(SearchParam searchParam);

    BatchResult selectOrderExecBotByCondition(SearchParam searchParam);

    BatchResult sumExecBotByCondition(SearchParam req);

    BatchResult sumExecByCondition(SearchParam req);

    BatchResult sumExecBot(SearchParam req);
}
