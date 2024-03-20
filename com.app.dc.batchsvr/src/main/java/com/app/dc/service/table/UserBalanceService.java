package com.app.dc.service.table;

import com.app.dc.entity.BatchResult;
import com.app.dc.entity.SearchParam;
import com.app.dc.entity.TUsersBalance;
import com.app.dc.entity.TUsersBalanceDay;
import com.app.dc.interfaces.IUserBalanceService;
import com.app.dc.service.day.CommonService;
import com.app.dc.utils.Consts;
import com.app.dc.utils.DateUtil;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Description
 * @Author
 * @Date
 **/
@Service
public class UserBalanceService extends CommonService implements IUserBalanceService {


    public void insertFromUserBalance(String tradeDate) {
        String strTime = " 00:00:00";
        String strEndTime = " 23:59:59";
        String startTime = tradeDate + strTime;
        String endTime = tradeDate + strEndTime;
        try {
        String insertDate = DateUtil.formatDate(DateUtil.addDays(DateUtil.convert2Date(tradeDate, DateUtil.YYYY_MM_DD), 1), DateUtil.YYYY_MM_DD);

        logger.info("tradeDate:{}, insertDate:{}", tradeDate, insertDate);

        String sql = "insert into dc_users_balance_day(trade_date, user_id,account_id,currency_id,currency,account_balance,used_balance,bonus_balance,lock_balance,update_time,close_by)" +
                " select ? as trade_date, user_id,account_id,currency_id,currency,account_balance,used_balance,bonus_balance,lock_balance,update_time,close_by" +
                " from dc_users_balance";

        int result = update(sql, new Object[] { tradeDate });

            logger.info("insertFromUserBalance result:" + result);
        } catch (Exception e) {
            logger.error("insertFromUserBalance exception:{}", e);
        }
    }


    public void deleteUserBalanceByDate(String tradeDate) {

        String tableName = "dc_users_balance_day";
        try {
            String delSql = "delete from " + tableName + " where trade_date = ?";

            int result = update(delSql, new Object[] { tradeDate });

            logger.info("deleteUserBalanceByDate size:" + result);
        } catch (Exception e) {
            logger.error("deleteUserBalanceByDate exception:{}", e);
        }

    }



    public void insertUserBalanceDay(List<TUsersBalanceDay> usersBalanceDayList) {

        BatchResult result = new BatchResult();
        String tableName = "dc_users_balance_day";
        try {
            int[] insertNum = insertBatch(usersBalanceDayList, tableName);

            logger.info("insertUserBalanceDay size:" + (insertNum==null?0:insertNum.length));
        } catch (Exception e) {
            result.code = Consts.NoKnowCode;
            result.msg = e.getMessage();
        }

    }


    /**
     * 查询dc_users_balance
     * @param req
     * @return
     */
    public BatchResult selectUserBalance(SearchParam req) {

        BatchResult result = new BatchResult();

        String tableName = "dc_users_balance";
        String orderByField = "create_time";

        try {
            String sql = CreateSql(req, tableName, false);
            String countSql = sql.replace("*", "count(*) as Counts");
            result.totalSize = getCount(countSql);
            String limitSql = CreateSql(req, tableName, true);
            List<TUsersBalance> usersBalanceList = getResult(limitSql, TUsersBalance.class);
            logger.info("selectUserBalance size:" + (usersBalanceList==null?0:usersBalanceList.size()));
            result.data = usersBalanceList;
        } catch (Exception e) {
            result.code = Consts.NoKnowCode;
            result.msg = e.getMessage();
        }
        return result;


    }


    public BatchResult updateUserBalance(TUsersBalance usersBalance) {
        BatchResult result = new BatchResult();

        String sql = "replace into dc_users_balance(user_id, account_id, currency_id, currency, account_balance, used_balance, bonus_balance, " +
                "lock_balance, update_time, close_by) values(?, ?, ?, ?, ?, ?, ?, ?, ?,?)";

        Object[] obj = new Object[]{ usersBalance.user_id, usersBalance.account_id, usersBalance.currency_id, usersBalance.currency, usersBalance.account_balance,
                usersBalance.used_balance, usersBalance.bonus_balance, usersBalance.lock_balance, usersBalance.update_time, usersBalance.close_by};

        try {
            int updateResult = update(sql, obj);
            result.data = updateResult;
            logger.info("updateUserBalance result:" + updateResult);

        } catch (Exception e) {
            result.code = Consts.NoKnowCode;
            result.msg = e.getMessage();
        }

        return result;
    }


}
