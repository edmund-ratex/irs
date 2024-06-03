package com.app.dc.service.day;

import com.app.common.utils.IdUtil;
import com.app.common.utils.StringUtil;
import com.app.dc.entity.*;
import com.app.dc.interfaces.*;
import com.app.dc.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * @Description
 * @Author
 * @Date
 **/
@Service
public class BatchUserDayTask {

    private Logger logger = LoggerFactory.getLogger(BatchUserDayTask.class);

    private boolean isRun = false;
    private boolean isCountRun = false;

    @Autowired
    private IUserBalanceService userBalanceDayService;
    @Autowired
    private IOrdersPositionService ordersPositionService;
    @Autowired
    private IMarkPriceService markPriceService;
    @Autowired
    private IPositionRptDayService positionRptDayService;
    @Autowired
    private IOrderExecService orderExecService;
    @Autowired
    private ITotalRptDayService totalRptDayService;
    @Autowired
    private IUserService userService;

    private String tradeDate;
    private boolean isFlagRun = false;

    public void setIsFlagRun(boolean flagRun) {
        this.isFlagRun = flagRun;
    }

    public void setTradeDate(String tradeDate) {
        this.tradeDate = tradeDate;
    }

    @Scheduled(cron = "${batchCron}")
    public void executeTask() {

        logger.info("start day batch task, isFlagRun:{}", isFlagRun);

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {

                String executeDate = DateUtil.formatDate(DateUtil.addDays(new Date(), -1), DateUtil.YYYY_MM_DD);

                if (!isFlagRun) {
                    if (StringUtil.isEmpty(tradeDate) || !executeDate.equalsIgnoreCase(tradeDate)) {
                        tradeDate = executeDate;
                    }
                } else {
                    isFlagRun = false;
                }

                try {
                    startTask(tradeDate);
                } catch (Exception e) {
                    logger.error("executeTask exception:{}", e);
                }
            }
        });

        t.setName("thread-batchdaytask");
        t.start();
    }

    @Scheduled(cron = "${batchCountCron}")
    public void executeCountTask() {
        logger.info("start day count batch task, isFlagRun:{}", isFlagRun);

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {

                String executeDate = DateUtil.formatDate(DateUtil.addDays(new Date(), -1), DateUtil.YYYY_MM_DD);
                if (!isFlagRun) {
                    if (StringUtil.isEmpty(tradeDate) || !executeDate.equalsIgnoreCase(tradeDate)) {
                        tradeDate = executeDate;
                    }
                } else {
                    isFlagRun = false;
                }

                try {
                    startCountTask(tradeDate);
                } catch (Exception e) {
                    logger.error("executeCountTask exception:{}", e);
                }
            }
        });

        t.setName("thread-batchCounttask");
        t.start();
    }

    public void start(String tradeDate) {

        logger.info("start task, tradeDate:{}", tradeDate);

        this.tradeDate = tradeDate;

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    String startDate = tradeDate;
                    String executeDate = DateUtil.formatDate(DateUtil.addDays(new Date(), -1), DateUtil.YYYY_MM_DD);

                    if (StringUtil.isEmpty(startDate) || !executeDate.equalsIgnoreCase(tradeDate)) {
                        startDate = executeDate;
                    }
                    startTask(startDate);
                    startCountTask(startDate);
                } catch (Exception ex) {
                    logger.error("BatchUserDayTask exception:{}", ex);
                } finally {
                    isRun = false;
                    isCountRun = false;
                }
            }
        });

        t.setName("thread-batchdaytask");
        t.start();

    }

    public void startCountTask(String tradeDate) {
        synchronized (this) {

            if (isRun) {
                logger.info("BatchUserDayTask is running");
                return;
            }

            if (isCountRun) {
                logger.info("startCountTask is running");
                return;
            } else {
                isCountRun = true;
            }
        }

        logger.info("startCountTask tradeDate:{}", tradeDate);

        sumPostionData(tradeDate);
        sumTotalData(tradeDate);

        logger.info("end startCountTask tradeDate:{}", tradeDate);

        isCountRun = false;
    }

    public void startTask(String tradeDate) {

        synchronized (this) {
            if (isRun) {
                logger.info("BatchUserDayTask is running");
                return;
            } else {
                isRun = true;
            }

            if (isCountRun) {
                logger.info("startCountTask is running");
                return;
            }
        }

        logger.info("startTask tradeDate:{}", tradeDate);
        userBalanceDayService.deleteUserBalanceByDate(tradeDate);
        userBalanceDayService.insertFromUserBalance(tradeDate);

        logger.info("start update dc_orders_position_day");
        ordersPositionService.deleteOrderPostionByDate(tradeDate);
        ordersPositionService.insertFromOrderPostion(tradeDate);

        logger.info("start update dc_mark_price_day");
        markPriceService.deleteMarketPriceByDate(tradeDate);
        markPriceService.insertFromCache(tradeDate);

        logger.info("end startTask tradeDate:{}", tradeDate);

        isRun = false;
    }

    private void sumTotalData(String tradeDate) {
        logger.info("sumTotalData start tradeDate:{}", tradeDate);

        SearchParam searchParam = new SearchParam();
        searchParam.setUpdate_time(tradeDate);
        int pageNum = 0;
        String insertDate = DateUtil.formatDate(DateUtil.addDays(DateUtil.convert2Date(tradeDate, DateUtil.YYYY_MM_DD), 1), DateUtil.YYYY_MM_DD);
        String preTradeDate = DateUtil.formatDate(DateUtil.addDays(DateUtil.convert2Date(tradeDate, DateUtil.YYYY_MM_DD), -1), DateUtil.YYYY_MM_DD);
        BigDecimal totalDaySize = BigDecimal.ZERO;
        BigDecimal totalDayFee = BigDecimal.ZERO;


        BatchResult sumExecBatchResult = orderExecService.sumExecByCondition(searchParam);
        if (sumExecBatchResult.data != null) {
            List<TSumOrderExec> sumOrderExecList = (List<TSumOrderExec>) sumExecBatchResult.data;
            if (sumOrderExecList != null && sumOrderExecList.size() > 0) {
                TSumOrderExec sumOrderExec = sumOrderExecList.get(0);
                if (sumOrderExec.total_exec_value != null) {
                    totalDaySize = totalDaySize.add(sumOrderExec.total_exec_value);
                }
                if (sumOrderExec.total_exec_fee != null) {
                    totalDayFee = totalDayFee.add(sumOrderExec.total_exec_fee);
                }
            }
        }

        logger.info("dc_orders_execorders totalDayFee:{}, totalDayValue:{}, tradeDate:{}", totalDayFee, totalDaySize, tradeDate);


        BatchResult sumExecBotBatchResult = orderExecService.sumExecBot(searchParam);//orderExecService.sumExecBotByCondition(searchParam);
        if (sumExecBotBatchResult.data != null) {
            List<TSumOrderExec> sumOrderExecList = (List<TSumOrderExec>) sumExecBotBatchResult.data;
            if (sumOrderExecList != null && sumOrderExecList.size() > 0) {
                TSumOrderExec sumOrderExec = sumOrderExecList.get(0);
                if (sumOrderExec.total_exec_value != null) {
                    totalDaySize = totalDaySize.add(sumOrderExec.total_exec_value);
                }
                if (sumOrderExec.total_exec_fee != null) {
                    totalDayFee = totalDayFee.add(sumOrderExec.total_exec_fee.multiply(new BigDecimal(-1)));
                }
            }
        }

        logger.info("totalDayFee:{}, totalDayValue:{}, tradeDate:{}", totalDayFee, totalDaySize, tradeDate);

        BigDecimal totalSize = totalDaySize;
        BigDecimal totalFee = totalDayFee;
        BigDecimal totalUsers = BigDecimal.ZERO;
        searchParam.setTrade_date(preTradeDate);
        BatchResult batchResult = totalRptDayService.selectTotalRpt(searchParam);
        if (batchResult.totalSize > 0) {
            List<TTotalRptDay> totalRptDayList = (List<TTotalRptDay>) batchResult.data;
            TTotalRptDay totalRptDay = totalRptDayList.get(0);
            logger.info("Pre totalRptDay:{}", totalRptDay);
            totalSize = totalSize.add(totalRptDay.total_volume);
            totalFee = totalFee.add(totalRptDay.total_fee);

            totalUsers = totalRptDay.total_users;
        }

        searchParam.setTrade_date("");
        searchParam.setCreate_time(tradeDate);
        searchParam.setUpdate_time("");
        BatchResult userBatchResult = userService.countUser(searchParam);

        BigDecimal totalDayUser = new BigDecimal(userBatchResult.totalSize);

        totalUsers = totalUsers.add(totalDayUser);

        TTotalRptDay totalRptDay = new TTotalRptDay();
        totalRptDay.close_by = "batchsvr";
        totalRptDay.create_time = DateUtil.getCurrentDate(DateUtil.YYYY_MM_DD + " " + DateUtil.HH_MM_SS);
        totalRptDay.fee = totalDayFee.setScale(2, RoundingMode.HALF_DOWN);
        totalRptDay.total_fee = totalFee.setScale(2, RoundingMode.HALF_DOWN);
        totalRptDay.users = totalDayUser;
        totalRptDay.total_users = totalUsers;
        totalRptDay.total_volume = totalSize.setScale(2, RoundingMode.HALF_DOWN);
        totalRptDay.volume = totalDaySize.setScale(2, RoundingMode.HALF_DOWN);
        totalRptDay.trade_date = tradeDate;

        totalRptDay.update_time = totalRptDay.create_time;

        totalRptDayService.deleteByDate(tradeDate);
        totalRptDayService.insert(totalRptDay);

        logger.info("sumTotalData end");
    }

    private void sumPostionData(String tradeDate) {
        logger.info("sumPostionData start");
        BatchResult markPriceResult= markPriceService.getMarketPriceByCondition(tradeDate);

        positionRptDayService.deletePositionRptDay(tradeDate);

        List<TMarkPriceDay> markPriceDayList = (List<TMarkPriceDay>)markPriceResult.data;

        SearchParam searchParam = new SearchParam();
        searchParam.setTrade_date(tradeDate);
        int pageNum = 0;
        Map<String, TPositionRptDay> positionRptDayMap = new HashMap<>();
        String insertDate = DateUtil.formatDate(DateUtil.addDays(DateUtil.convert2Date(tradeDate, DateUtil.YYYY_MM_DD), 1), DateUtil.YYYY_MM_DD);
        while (true) {
            searchParam.setPage_num(pageNum);

            BatchResult batchResult = ordersPositionService.selectOrderPostionDayByPage(searchParam);

            List<TOrdersPositionDay> positionDayList = (List<TOrdersPositionDay>) batchResult.data;
            logger.info("batchResult totalSize:{}, positionDayList size:{}", batchResult.totalSize, positionDayList.size());
            if (batchResult.totalSize > 0 && positionDayList.size() > 0) {
                logger.info("selectOrderPostionDayByPage pageNum:{}", pageNum);
                pageNum++;
//                List<TOrdersPositionDay> positionDayList = (List<TOrdersPositionDay>) batchResult.data;
                for (TOrdersPositionDay ordersPositionDay: positionDayList) {
                    TMarkPriceDay markPriceDay = getMarkPriceDay(ordersPositionDay.symbol, markPriceDayList);
                    if (positionRptDayMap.containsKey(ordersPositionDay.symbol)) {
                        TPositionRptDay positionRptDay = positionRptDayMap.get(ordersPositionDay.symbol);

                        if (markPriceDay != null && markPriceDay.mark_price != null) {
                            positionRptDay.total_value = positionRptDay.total_value.add(ordersPositionDay.size.multiply(markPriceDay.mark_price));
//                            logger.info("Symbol:{}, TPositionRptDay total_value:{}", ordersPositionDay.symbol, positionRptDay.total_value);

                            logger.info("Update cal symbol:{}, size:{}, mark_price:{}, total_value:{}, add value:{}",
                                    positionRptDay.symbol, ordersPositionDay.size.toPlainString(), markPriceDay==null?null:markPriceDay.mark_price.toPlainString(), positionRptDay.total_value.toPlainString(),
                                    ordersPositionDay.size.multiply(markPriceDay.mark_price).toPlainString());

                        }
                    } else {
                        TPositionRptDay positionRptDay = new TPositionRptDay();
                        if (markPriceDay != null && markPriceDay.mark_price != null)
                            positionRptDay.total_value = ordersPositionDay.size.multiply(markPriceDay.mark_price);
                        positionRptDay.close_by = "batchsvr";
                        positionRptDay.create_time = DateUtil.getCurrentDate(DateUtil.YYYY_MM_DD + " " + DateUtil.HH_MM_SS);
                        positionRptDay.symbol = ordersPositionDay.symbol;
                        positionRptDay.update_time = positionRptDay.create_time;
                        positionRptDay.id = IdUtil.getId();
                        positionRptDay.trade_date = tradeDate;

                        logger.info("Add cal symbol:{}, size:{}, mark_price:{}, total_value:{}",
                                positionRptDay.symbol, ordersPositionDay.size.toPlainString(), markPriceDay==null?null:markPriceDay.mark_price.toPlainString(), positionRptDay.total_value.toPlainString());

                        positionRptDayMap.put(ordersPositionDay.symbol, positionRptDay);
                    }

                }

                if (positionDayList.size() < searchParam.getPage_size()) {
                    break;
                }


                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                }
            } else {
                break;
            }
        }
        logger.info("positionRptDayMap size:{}", positionRptDayMap.size());
        List<TPositionRptDay> positionRptDayList = new ArrayList<>();

        positionRptDayList.addAll(positionRptDayMap.values());

        for (TPositionRptDay positionRptDay: positionRptDayList) {
            positionRptDay.total_value = positionRptDay.total_value.setScale(2, RoundingMode.HALF_DOWN);
        }

        positionRptDayService.batchInsert(positionRptDayList);

        logger.info("sumPostionData end");

    }

    private TMarkPriceDay getMarkPriceDay(String symbol, List<TMarkPriceDay> markPriceDayList) {
        TMarkPriceDay result = null;

        for (TMarkPriceDay mp: markPriceDayList) {

            if (mp.symbol.equalsIgnoreCase(symbol)) {
                result = mp;
                break;
            }

        }
        return result;
    }


}
