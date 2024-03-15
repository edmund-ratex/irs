package com.app.dc.interfaces;

import com.app.dc.entity.TPositionRptDay;

import java.util.List;

/**
 * @Description
 * @Author
 * @Date
 **/
public interface IPositionRptDayService {

    void batchInsert(List<TPositionRptDay> positionRptDayList);

    void deletePositionRptDay(String tradeDate);

}
