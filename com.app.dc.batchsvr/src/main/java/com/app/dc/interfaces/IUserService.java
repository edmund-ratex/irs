package com.app.dc.interfaces;

import com.app.dc.entity.BatchResult;
import com.app.dc.entity.SearchParam;

/**
 * @Description
 * @Author
 * @Date
 **/
public interface IUserService {

    BatchResult countUser(SearchParam req);
    BatchResult selectUserByAddress(SearchParam req);
}
