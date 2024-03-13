package com.app.dc.service.table;

import com.app.common.utils.StringUtil;
import com.app.dc.entity.BatchResult;
import com.app.dc.entity.SearchParam;
import com.app.dc.entity.TOrdersPositionDay;
import com.app.dc.interfaces.IOrdersPositionService;
import com.app.dc.service.day.CommonService;
import com.app.dc.utils.Consts;
import com.app.dc.utils.DateUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @Author
 * @Date
 **/
@Service
public class OrdersPostionService extends CommonService implements IOrdersPositionService {



    public BatchResult selectOrderPostionDayByPage(SearchParam req) {

        BatchResult result = new BatchResult();
        String tableName = "dc_orders_position_day";
        String orderByField = "update_time";
        if (StringUtil.isEmpty(req.getSort_field()))
            req.setSort_field(orderByField);
        try {
            String sql = CreateSql(req, tableName, false);
            String countSql = sql.replace("*", "count(*) as Counts");
            result.totalSize = getCount(countSql);
            String limitSql = CreateSql(req, tableName, true);
            List<TOrdersPositionDay> orderPopsitionList = getResult(limitSql, TOrdersPositionDay.class);
            result.data = orderPopsitionList;
            logger.info("selectOrderPostionDayByPage size:" + (orderPopsitionList == null ? 0 : orderPopsitionList.size()));
        } catch (Exception e) {
            result.code = Consts.NoKnowCode;
            result.msg = e.getMessage();
        }
        return result;

    }


    @Override
    public void insertFromOrderPostion(String tradeDate) {
        String strTime = " 00:00:00";
        String strEndTime = " 23:59:59";
        String startTime = tradeDate + strTime;
        String endTime = tradeDate + strEndTime;
        try {
            String insertDate = DateUtil.formatDate(DateUtil.addDays(DateUtil.convert2Date(tradeDate, DateUtil.YYYY_MM_DD), 1), DateUtil.YYYY_MM_DD);

            logger.info("tradeDate:{}, insertDate:{}", tradeDate, insertDate);

            String sql = "insert into dc_orders_position_day(trade_date, user_id,account_id,symbol_id,symbol,currency_id,currency,side,status, leverage,init_margin_rate,maint_margin_rate,risk_limit," +
                    " size,value,avg_entry_px,pos_cost,assigned_pos_balance,pos_balance,bank_rupt_comm_fee,position_margin,display_leverage,bank_rupt_price,liq_price," +
                    " deleverage_percentile,buy_value_to_cost,sell_value_to_cost,buy_leaves_qty,buy_leaves_value,sell_leaves_qty,sell_leaves_value,free_qty,free_cost,order_cost," +
                    " closed_pnl,update_time,close_by,external_create_time) " +
                    " select ? as trade_date, user_id,account_id,symbol_id,symbol,currency_id,currency,side,status, leverage,init_margin_rate,maint_margin_rate,risk_limit," +
                    " size,value,avg_entry_px,pos_cost,assigned_pos_balance,pos_balance,bank_rupt_comm_fee,position_margin,display_leverage,bank_rupt_price,liq_price," +
                    " deleverage_percentile,buy_value_to_cost,sell_value_to_cost,buy_leaves_qty,buy_leaves_value,sell_leaves_qty,sell_leaves_value,free_qty,free_cost,order_cost," +
                    " closed_pnl,update_time,close_by,external_create_time from dc_orders_position";

            int result = update(sql, new Object[] { tradeDate });

            logger.info("insertFromOrderPostion result:" + result);
        } catch (Exception e) {
            logger.error("insertFromOrderPostion exception:{}", e);
        }
    }

    @Override
    public void deleteOrderPostionByDate(String tradeDate) {
        String tableName = "dc_orders_position_day";
        try {
            String delSql = "delete from " + tableName + " where trade_date = ?";

            int result = update(delSql, new Object[] { tradeDate });

            logger.info("deleteOrderPostionByDate size:" + result);
        } catch (Exception e) {
            logger.error("deleteOrderPostionByDate exception:{}", e);
        }
    }
}
