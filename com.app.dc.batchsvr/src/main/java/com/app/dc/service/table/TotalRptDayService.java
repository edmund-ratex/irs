package com.app.dc.service.table;

import com.app.common.condition.BuildConditionType;
import com.app.common.condition.Condition;
import com.app.common.condition.ConditionInfo;
import com.app.common.condition.ConditionOperator;
import com.app.common.db.DBUtils;
import com.app.dc.entity.BatchResult;
import com.app.dc.entity.SearchParam;
import com.app.dc.entity.TMarkPriceDay;
import com.app.dc.entity.TTotalRptDay;
import com.app.dc.interfaces.ITotalRptDayService;
import com.app.dc.service.day.CommonService;
import org.apache.http.util.TextUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author
 * @Date
 **/
@Service
public class TotalRptDayService extends CommonService implements ITotalRptDayService {

    private String tableName = "dc_total_rpt_day";

    @Override
    public void deleteByDate(String tradeDate) {
        try {
            String delSql = "delete from " + tableName + " where trade_date = ?";

            int result = update(delSql, new Object[] { tradeDate });

            logger.info("deleteByDate size:" + result);
        } catch (Exception e) {
            logger.error("deleteByDate exception:{}", e);
        }
    }

    @Override
    public void insert(TTotalRptDay totalRptDay) {

        List<TTotalRptDay> totalRptDayList = new ArrayList<>();
        totalRptDayList.add(totalRptDay);

        try {
            int[] result = insertBatch(totalRptDayList, tableName);
            logger.info("insert result:" + (result==null?0:result.length));
        } catch (Exception e) {
            logger.error("insert exception:{}", e);
        }

    }

    @Override
    public BatchResult selectTotalRpt(SearchParam searchParam) {

        BatchResult result = new BatchResult();
        Condition condition = new Condition();
        condition.setTableName(tableName);
        if (!TextUtils.isEmpty(searchParam.getTrade_date())) {

            ConditionInfo bid = new ConditionInfo("trade_date", searchParam.getTrade_date(), ConditionOperator.EqualTo, true);
            condition.AndCondition(bid);

        }

        try {
            String sql = (String) condition.BuildCondition(BuildConditionType.MySql);

            List<TTotalRptDay> totalRptDayList = getResult(sql, TTotalRptDay.class);
            result.data = totalRptDayList;
            result.totalSize = (totalRptDayList == null ? 0 : totalRptDayList.size());
            logger.info("selectTotalRpt size:" + (totalRptDayList == null ? 0 : totalRptDayList.size()));
        } catch (Exception e) {
            logger.error("BuildCondition exception:{}", e);
        }

        return result;


    }
}
