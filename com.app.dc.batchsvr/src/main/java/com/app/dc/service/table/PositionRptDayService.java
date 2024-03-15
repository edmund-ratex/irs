package com.app.dc.service.table;

import com.app.common.db.DBUtils;
import com.app.dc.entity.TPositionRptDay;
import com.app.dc.interfaces.IPositionRptDayService;
import com.app.dc.service.day.CommonService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @Author
 * @Date
 **/
@Service
public class PositionRptDayService extends CommonService implements IPositionRptDayService {

    private String tableName = "dc_position_rpt_day";

    @Override
    public void batchInsert(List<TPositionRptDay> positionRptDayList) {

        try {

            int[] result = DBUtils.insertList(positionRptDayList, tableName);

            logger.info("batchInsert result:" + (result==null?0:result.length));

        } catch (Exception e) {
            logger.error("batchInsert exception:{}", e);
        }

    }

    @Override
    public void deletePositionRptDay(String tradeDate) {
        try {
            String delSql = "delete from " + tableName + " where trade_date = ?";

            int result = update(delSql, new Object[] { tradeDate });

            logger.info("deletePositionRptDay size:" + result);
        } catch (Exception e) {
            logger.error("deletePositionRptDay exception:{}", e);
        }
    }


}
