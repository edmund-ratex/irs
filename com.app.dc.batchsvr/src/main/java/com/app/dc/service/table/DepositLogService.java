package com.app.dc.service.table;

import com.app.common.db.DBUtils;
import com.app.dc.entity.TDepositLog;
import com.app.dc.interfaces.IDepositLogService;
import com.app.dc.service.day.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepositLogService extends CommonService implements IDepositLogService {

    private Logger logger = LoggerFactory.getLogger(CashLogService.class);
    private String tableName = "dc_deposit_log";

    @Override
    public void insertDepositLog(List<TDepositLog> depositLogList) {
        try {

            int[] result = DBUtils.insertList(depositLogList, tableName);

            logger.info("insertDepositLog result:" + (result==null?0:result.length));

        } catch (Exception e) {
            logger.error("batchInsert exception:{}", e);
        }
    }

    public void updateDepositLog(int status, String txHash) {

        try {
            logger.info("updateDepositLog param:{} {}", status, txHash);
            String updateSql = "update " + tableName + " set status = ? where txHash = ?";

            int result = update(updateSql, new Object[] { status, txHash });

            logger.info("updateDepositLog size:" + result);
        } catch (Exception e) {
            logger.error("updateDepositLog exception:{}", e);
        }
    }
}
