package com.app.dc.service.day;

import com.app.common.condition.*;
import com.app.common.db.DBUtils;
import com.app.common.utils.StringUtil;
import com.app.dc.entity.SearchParam;
import com.mysql.jdbc.StringUtils;
import org.apache.http.util.TextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author
 * @Date
 **/
public class CommonService {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    public static Object getCount(String sql, Object[] params, Class type) throws Exception {

        Object o =  DBUtils.querySimpleObject(sql, params, type);

        return o;
    }


    public int update(String sql, Object[] args) throws Exception {

        return DBUtils.update(sql, args);

    }

    public static <T> List<T> getResult(String sql, Class<T> type) throws Exception {

        return DBUtils.queryListThrowsException(sql, new Object[] {}, type);
    }

    public <T> int[] insertBatch(List<T> list, String tableName) throws Exception {
        return DBUtils.insertList(list, tableName);
    }

    public static int getCount(String sql) throws Exception {

        Object o = DBUtils.querySimpleObject(sql, null, int.class);

        return ((Integer) o).intValue();
    }

    public String CreateSql(SearchParam ernr, String tableName, boolean isPage)
            throws Exception {
        String strTime = " 00:00:00";
        String strEndTime = " 23:59:59";
        Condition condition = new Condition();
        condition.setTableName(tableName);

        if (!TextUtils.isEmpty(ernr.getTrade_date())) {
            String trade_date = ernr.getTrade_date();

            ConditionInfo bid = new ConditionInfo("trade_date", trade_date, ConditionOperator.EqualTo, true);
            condition.AndCondition(bid);

        }

        if (!StringUtils.isNullOrEmpty(ernr.getCreate_time())) {

            ConditionInfo bidBegin = new ConditionInfo("create_time", ernr.getCreate_time() + strTime,
                    ConditionOperator.GreaterThanOrEqualTo, true);
            ConditionInfo bidEnd = new ConditionInfo("create_time", ernr.getCreate_time() + strEndTime,
                    ConditionOperator.LessThanOrEqualTo, true);
            condition.AndCondition(bidBegin);
            condition.AndCondition(bidEnd);

        }

        if (!StringUtils.isNullOrEmpty(ernr.getUpdate_time())) {

            ConditionInfo bidBegin = new ConditionInfo("update_time", ernr.getUpdate_time() + strTime,
                    ConditionOperator.GreaterThanOrEqualTo, true);
            ConditionInfo bidEnd = new ConditionInfo("update_time", ernr.getUpdate_time() + strEndTime,
                    ConditionOperator.LessThanOrEqualTo, true);
            condition.AndCondition(bidBegin);
            condition.AndCondition(bidEnd);

        }

        if (!StringUtils.isNullOrEmpty(ernr.getStart_cash_time()) && !StringUtils.isNullOrEmpty(ernr.getEnd_cash_time())) {
            ConditionInfo bidBegin = new ConditionInfo("update_time", ernr.getStart_cash_time(),
                    ConditionOperator.GreaterThanOrEqualTo, true);
            ConditionInfo bidEnd = new ConditionInfo("update_time", ernr.getEnd_cash_time(),
                    ConditionOperator.LessThanOrEqualTo, true);
            condition.AndCondition(bidBegin);
            condition.AndCondition(bidEnd);
        }

        if (!TextUtils.isEmpty(ernr.getCash_side())) {
            String side = ernr.getCash_side();

            ConditionInfo bid = new ConditionInfo("side", side, ConditionOperator.EqualTo, true);
            condition.AndCondition(bid);

        }


//        if (!StringUtils.isNullOrEmpty(ernr.getTrade_date())) {
//
//            ConditionInfo bidBegin = new ConditionInfo("update_time", ernr.getTrade_date() + strTime,
//                    ConditionOperator.GreaterThanOrEqualTo, true);
//            ConditionInfo bidEnd = new ConditionInfo("update_time", ernr.getTrade_date() + strEndTime,
//                    ConditionOperator.LessThanOrEqualTo, true);
//            condition.AndCondition(bidBegin);
//            condition.AndCondition(bidEnd);
//
//        }

//        if (StringUtil.isNotEmpty(ernr.getSort_field())) {
//            OrderInfo e = new OrderInfo();
//            e.setFieldName(ernr.getSort_field());
//            if (StringUtil.isNotEmpty(ernr.getSort_type())) {
//                if (ernr.getSort_type().equalsIgnoreCase(OrderType.Desc.name()))
//                    e.setOrderType(com.app.common.condition.OrderType.Desc);
//                else
//                    e.setOrderType(OrderType.Asc);
//            } else
//                e.setOrderType(com.app.common.condition.OrderType.Desc);
//
//            condition.getOrderInfos().add(e);
//        }
        String sql = (String) condition.BuildCondition(BuildConditionType.MySql);
        if (isPage) {
            sql = sql + String.format(" limit %s,%s", ernr.getPage_num() * ernr.getPage_size(), ernr.getPage_size());
        }
        logger.info(sql);
        return sql;
    }

}
