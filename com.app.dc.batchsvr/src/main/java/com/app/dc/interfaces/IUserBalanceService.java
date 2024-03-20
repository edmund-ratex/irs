package com.app.dc.interfaces;

import com.app.dc.entity.BatchResult;
import com.app.dc.entity.SearchParam;
import com.app.dc.entity.TUsersBalance;
import com.app.dc.entity.TUsersBalanceDay;

import java.util.List;

/**
 * @Description
 * @Autho
 * @Date
 **/
public interface IUserBalanceService {

    void insertFromUserBalance(String tradeDate);

    void deleteUserBalanceByDate(String tradeDate);

    BatchResult selectUserBalance(SearchParam searchParam);

    void insertUserBalanceDay(List<TUsersBalanceDay> usersBalanceDayList);

    BatchResult updateUserBalance(TUsersBalance usersBalance);
}
