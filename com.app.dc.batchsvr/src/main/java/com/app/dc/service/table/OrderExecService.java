package com.app.dc.service.table;

import com.app.common.db.DBUtils;
import com.app.common.utils.JsonUtils;
import com.app.common.utils.StringUtil;
import com.app.dc.entity.*;
import com.app.dc.interfaces.IOrderExecService;
import com.app.dc.service.day.CommonService;
import com.app.dc.util.CommonUtil;
import com.app.dc.utils.Consts;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;

/**
 * @Description
 * @Author
 * @Date
 **/
@Service
public class OrderExecService extends CommonService implements IOrderExecService {

    private String tableName = "dc_orders_execorders";
    private String table_exec_bot = "dc_orders_execorders_robot";
    @Override
    public BatchResult selectOrderExecByCondition(SearchParam req) {
        BatchResult result = new BatchResult();
        String orderByField = "update_time";
        if (StringUtil.isEmpty(req.getSort_field()))
            req.setSort_field(orderByField);
        try {
            String sql = CreateSql(req, tableName, false);
            sql += " and user_id != '" + CommonUtil.Exange_User_id + "'";
            String countSql = sql.replace("*", "count(*) as Counts");
            logger.info("countSql:{}", countSql);
            result.totalSize = getCount(countSql);
            String limitSql = CreateSql(req, tableName, true);
            limitSql =  limitSql.replace("limit", " and user_id != '" + CommonUtil.Exange_User_id + "' limit ");
            logger.info("limitSql:{}", limitSql);
            List<TOrdersExecorders> ordersExecordersList = getResult(limitSql, TOrdersExecorders.class);
            result.data = ordersExecordersList;
            logger.info("selectOrderExecByCondition size:" + (ordersExecordersList == null ? 0 : ordersExecordersList.size()));
        } catch (Exception e) {
            result.code = Consts.NoKnowCode;
            result.msg = e.getMessage();
        }
        return result;
    }

    @Override
    public BatchResult selectOrderExecBotByCondition(SearchParam req) {
        BatchResult result = new BatchResult();
        String orderByField = "update_time";
        if (StringUtil.isEmpty(req.getSort_field()))
            req.setSort_field(orderByField);
        try {
            String sql = CreateSql(req, tableName, false);
            sql += " and user_id != '" + CommonUtil.Exange_User_id + "'";
            String countSql = sql.replace("*", "count(*) as Counts");
            logger.info("countSql:{}", countSql);
            result.totalSize = getCount(countSql);
            String limitSql = CreateSql(req, table_exec_bot, true);
            limitSql =  limitSql.replace("limit", " and user_id != '" + CommonUtil.Exange_User_id + "' limit ");
            logger.info("limitSql:{}", limitSql);
            List<TOrdersExecorders> ordersExecordersList = getResult(limitSql, TOrdersExecorders.class);
            result.data = ordersExecordersList;
            logger.info("selectOrderExecBotByCondition size:" + (ordersExecordersList == null ? 0 : ordersExecordersList.size()));
        } catch (Exception e) {
            result.code = Consts.NoKnowCode;
            result.msg = e.getMessage();
        }
        return result;
    }

    @Override
    public BatchResult sumExecByCondition(SearchParam req) {
        BatchResult result = new BatchResult();
        Connection con = null;
        try {
            String sql = CreateSql(req, tableName, false);
            String sumSql = sql.replace("*", "sum(exec_value) as total_exec_value, sum(exec_fee) as total_exec_fee");
            logger.info("sumExecByCondition sql:{}", sumSql);

            con = DBUtils.getDatabaseConnection().getConnection();
            con.setAutoCommit(false);

            List<TSumOrderExec> sumOrderExecList = DBUtils.queryListThrowsException(sumSql, new Object[] {}, TSumOrderExec.class, con);
            result.data = sumOrderExecList;

            con.setAutoCommit(true);

        } catch (Exception e) {
            logger.error("sumExecByCondition exception:{}", e);
            result.code = Consts.NoKnowCode;
            result.msg = e.getMessage();
        } finally {
            if (con != null) {
                try {

                    DBUtils.getDatabaseConnection().freeConnection(con);
                } catch (Exception e1) {

                }
            }
        }
        logger.info("sumExecByCondition result:{}", JsonUtils.Serializer(result));
        return result;
    }

    @Override
    public BatchResult sumExecBotByCondition(SearchParam req) {
        BatchResult result = new BatchResult();
        Connection con = null;
        try {
            String sql = CreateSql(req, table_exec_bot, false);
            String sumSql = sql.replace("*", "sum(exec_value) as total_exec_value, sum(exec_fee) as total_exec_fee");
            logger.info("sumExecBotByCondition sql:{}", sumSql);

            con = DBUtils.getDatabaseConnection().getConnection();
            con.setAutoCommit(false);

            List<TSumOrderExec> sumOrderExecList = DBUtils.queryListThrowsException(sumSql, new Object[] {}, TSumOrderExec.class, con);
            result.data = sumOrderExecList;

            con.setAutoCommit(true);

        } catch (Exception e) {
            logger.error("sumExecBotByCondition exception:{}", e);
            result.code = Consts.NoKnowCode;
            result.msg = e.getMessage();
        } finally {
            if (con != null) {
                try {

                    DBUtils.getDatabaseConnection().freeConnection(con);
                } catch (Exception e1) {

                }
            }
        }
        logger.info("sumExecBotByCondition result:{}", JsonUtils.Serializer(result));
        return result;
    }


    public BatchResult sumExecBot(SearchParam req) {
        BatchResult result = new BatchResult();
        Connection con = null;
        try {
//            String sql = "select sum(total_volume) as total_exec_value, sum(total_fee) as total_exec_fee from dc_user_total_robot where user_id != ? and trade_date = ?";
            String sql = "select sum(total_volume) as total_exec_value, sum(total_fee) as total_exec_fee from dc_user_total_robot where user_id NOT LIKE '88888-%' and trade_date = ?";
            logger.info("sumExecBot sql:{}", sql);
            con = DBUtils.getDatabaseConnection().getConnection();
            con.setAutoCommit(false);

            List<TSumOrderExec> sumOrderExecList = DBUtils.queryListThrowsException(sql, new Object[] { req.getUpdate_time()}, TSumOrderExec.class, con);
            result.data = sumOrderExecList;

            con.setAutoCommit(true);

        } catch (Exception e) {
            logger.error("sumExecBot exception:{}", e);
            result.code = Consts.NoKnowCode;
            result.msg = e.getMessage();
        } finally {
            if (con != null) {
                try {

                    DBUtils.getDatabaseConnection().freeConnection(con);
                } catch (Exception e1) {

                }
            }
        }
        logger.info("sumExecBot result:{}", JsonUtils.Serializer(result));
        return result;
    }
}
