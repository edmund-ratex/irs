package com.app.dc.interfaces;

import com.app.dc.entity.BatchResult;
import com.app.dc.entity.SearchParam;
import com.app.dc.entity.TDepositLog;

import java.util.List;

public interface IDepositLogService {


    void insertDepositLog(List<TDepositLog> depositLogList);
    void updateDepositLog(int status, String txHash);
}
