package com.app.dc.service.table;

import com.app.common.utils.StringUtil;
import com.app.dc.entity.BatchResult;
import com.app.dc.entity.SearchParam;
import com.app.dc.entity.TCashLog;
import com.app.dc.entity.TOrdersExecorders;
import com.app.dc.interfaces.ICashLogService;
import com.app.dc.service.day.CommonService;
import com.app.dc.utils.Consts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CashLogService extends CommonService implements ICashLogService {

    private Logger logger = LoggerFactory.getLogger(CashLogService.class);
    private String tableName = "dc_cash_log";

    @Override
    public BatchResult selectCashLogByCondition(SearchParam req) {
        BatchResult result = new BatchResult();
        logger.info("selectCashLogByCondition param:{}", req.toString());
        try {
            String sql = CreateSql(req, tableName, false);
            List<TCashLog> cashLogList = getResult(sql, TCashLog.class);
            result.data = cashLogList;
            logger.info("selectCashLogByCondition size:" + (cashLogList == null ? 0 : cashLogList.size()));
        } catch (Exception e) {
            result.code = Consts.NoKnowCode;
            result.msg = e.getMessage();
        }
        return result;
    }
}
