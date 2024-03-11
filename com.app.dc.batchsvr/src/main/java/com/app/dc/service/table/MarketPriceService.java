package com.app.dc.service.table;

import com.app.common.condition.BuildConditionType;
import com.app.common.condition.Condition;
import com.app.common.condition.ConditionInfo;
import com.app.common.condition.ConditionOperator;
import com.app.dc.entity.BatchResult;
import com.app.dc.entity.TMarkPriceDay;
import com.app.dc.entity.TOrdersPositionDay;
import com.app.dc.interfaces.IMarkPriceService;
import com.app.dc.po.TTicker;
import com.app.dc.service.day.CommonService;
import com.app.dc.utils.DateUtil;
import com.app.dc.utils.MDHistSvrClient;
import org.apache.http.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.dsig.keyinfo.KeyValue;
import javax.xml.ws.ServiceMode;
import java.math.BigDecimal;
import java.util.*;

/**
 * @Description
 * @Author
 * @Date
 **/
@Service
public class MarketPriceService extends CommonService implements IMarkPriceService {
    private String tableName = "dc_mark_price_day";
    @Autowired
    private MDHistSvrClient mdHistSvrClient;


    public BatchResult getMarketPriceByCondition(String tradeDate) {
        BatchResult result = new BatchResult();
        Condition condition = new Condition();
        condition.setTableName(tableName);
        if (!TextUtils.isEmpty(tradeDate)) {

            ConditionInfo bid = new ConditionInfo("trade_date", tradeDate, ConditionOperator.EqualTo, true);
            condition.AndCondition(bid);

        }

        try {
            String sql = (String) condition.BuildCondition(BuildConditionType.MySql);
            logger.info("getMarketPriceByCondition sql:{}", sql);
            List<TMarkPriceDay> markPriceDayList = getResult(sql, TMarkPriceDay.class);
            result.data = markPriceDayList;
            logger.info("getMarketPriceByCondition size:" + (markPriceDayList == null ? 0 : markPriceDayList.size()));
        } catch (Exception e) {
            logger.error("BuildCondition exception:{}", e);
        }

        return result;
    }

    @Override
    public void insertFromCache(String tradeDate) {

        HashMap<String, TTicker> tickerHashMap = mdHistSvrClient.marketPriceSearch("", "");
        logger.info("marketPriceSearch size:{}", tickerHashMap==null?0:tickerHashMap.size());

        Collection<TTicker> collection = tickerHashMap.values();

//        String tradeDate = DateUtil.formatDate(new Date(), DateUtil.YYYY_MM_DD);
                //DateUtil.formatDate(DateUtil.addDays(new Date(), 1), DateUtil.YYYY_MM_DD);
        List<TMarkPriceDay> markPriceDayList = new ArrayList<>();
        for (TTicker ticker: collection) {

            TMarkPriceDay markPriceDay = new TMarkPriceDay();
            markPriceDay.close_by = "batchsvr";
            markPriceDay.create_time = DateUtil.getCurrentDate(DateUtil.YYYY_MM_DD + " " + DateUtil.HH_MM_SS);
            markPriceDay.index_price = new BigDecimal(ticker.getIndex_price());
            markPriceDay.mark_price = new BigDecimal(ticker.getMark_price());
            markPriceDay.trade_date = tradeDate;
            markPriceDay.update_time = String.valueOf(ticker.getTimestamp());
            markPriceDay.symbol = ticker.getSymbol();

            markPriceDayList.add(markPriceDay);
        }

        try {
            int[] result = insertBatch(markPriceDayList, tableName);
            logger.info("insertFromCache result:" + (result==null?0:result.length));
        } catch (Exception e) {
            logger.error("insertFromCache exception:{}", e);
        }
    }

    @Override
    public void deleteMarketPriceByDate(String tradeDate) {
        try {
            String delSql = "delete from " + tableName + " where trade_date = ?";

            int result = update(delSql, new Object[] { tradeDate });

            logger.info("deleteMarketPriceByDate size:" + result);
        } catch (Exception e) {
            logger.error("deleteMarketPriceByDate exception:{}", e);
        }
    }
}
