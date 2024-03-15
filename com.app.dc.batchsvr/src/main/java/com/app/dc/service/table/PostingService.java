package com.app.dc.service.table;

import com.app.dc.entity.BatchResult;
import com.app.dc.interfaces.IPostingService;
import com.app.dc.service.day.CommonService;
import com.app.dc.util.CommonUtil;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @Description
 * @Author
 * @Date
 **/
@Service
public class PostingService extends CommonService implements IPostingService {

    private String tableName = "dc_posting";

    @Override
    public BatchResult countFeeRate(String tradeDate) {

        BatchResult batchResult = new BatchResult();

        String strTime = " 00:00:00";
        String strEndTime = " 23:59:59";

        try {

            String start = tradeDate + strTime;
            String end = tradeDate + strEndTime;

            String sumSql = "select sum(amount) as total_fee_rate from " + tableName + " where user_id = ? and create_time > ? and create_time < ?";
            Object obj = getCount(sumSql, new Object[] { CommonUtil.Exchange_Fee_rate_user_id, start, end }, BigDecimal.class);

//            String sumRobotSlq = "select total_fee from dc_user_total_robot where user_id = ? and trade_date = ?";
            String sumRobotSlq = "select sum(total_fee) as total_fee_robot from dc_user_total_robot where user_id LIKE '88888-%' and trade_date = ?";
            Object robotSumFee = getCount(sumRobotSlq, new Object[] { tradeDate }, BigDecimal.class);

            String accSql = "select account_balance from dc_users_balance where user_id = ?";
            Object balance = getCount(accSql, new Object[] { CommonUtil.Exchange_Fee_rate_user_id }, BigDecimal.class);

            logger.info("obj:{}, robotSumFee:{}, balance:{}", obj, robotSumFee, balance);

            BigDecimal sumBd = (BigDecimal) obj;
            BigDecimal balanceBd = (BigDecimal) balance;
            BigDecimal robotSumBd = (BigDecimal) robotSumFee;

            batchResult.data = sumBd.add(balanceBd).add(robotSumBd);

            logger.info("countFeeRate result:" + obj);
        } catch (Exception e) {
            logger.error("deletePositionRptDay exception:{}", e);
        }

        return batchResult;
    }
}
