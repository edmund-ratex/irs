package com.app.dc.service.day;

import com.app.common.utils.StringUtil;
import com.app.dc.entity.BatchResult;
import com.app.dc.entity.TUsersBalance;
import com.app.dc.interfaces.IPostingService;
import com.app.dc.interfaces.IUserBalanceService;
import com.app.dc.util.CommonUtil;
import com.app.dc.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @Description
 * @Author
 * @Date
 **/
@Service
public class FeeRateDayTask {

    private Logger logger = LoggerFactory.getLogger(FeeRateDayTask.class);
    private boolean isRun = false;

    @Autowired
    private IUserBalanceService userBalanceDayService;
    private String tradeDate;
    @Autowired
    private IPostingService postingService;

    @Scheduled(cron = "${batchCron}")
    public void executeTask() {

        logger.info("start count fee_rate task");

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {

                String startDate = tradeDate;
                if (StringUtil.isEmpty(startDate)) {
                    startDate = DateUtil.getCurrentDate(DateUtil.YYYY_MM_DD);
                }
                startTask(startDate);
            }
        });

        t.setName("thread-batchdaytask");
        t.start();
    }


    public void start(String tradeDate) {

        logger.info("start task");

        this.tradeDate = tradeDate;

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {

                String startDate = tradeDate;
                if (StringUtil.isEmpty(startDate)) {
                    startDate = DateUtil.getCurrentDate(DateUtil.YYYY_MM_DD);
                }
                startTask(startDate);
            }
        });

        t.setName("thread-batchdaytask");
        t.start();

    }


    public void startTask(String tradeDate) {

        synchronized (this) {
            if (isRun) {
                logger.info("FeeRateDayTask is running");
                return;
            } else {
                isRun = true;
            }
        }

        logger.info("startTask tradeDate:{}" + tradeDate);

        BatchResult batchResult = postingService.countFeeRate(tradeDate);

        TUsersBalance usersBalance = new TUsersBalance();
        usersBalance.user_id = CommonUtil.Exchange_Fee_rate_user_id;
        usersBalance.account_id = new BigDecimal(CommonUtil.Exchange_Fee_rate_user_id);
        usersBalance.account_balance = (BigDecimal) batchResult.data;
        usersBalance.close_by = "BatchSvr";
        usersBalance.update_time = DateUtil.getCurrentDate(DateUtil.YYYY_MM_DD + " " + DateUtil.HH_MM_SS);
        usersBalance.currency = "USDT";
        usersBalance.currency_id = 1;

        userBalanceDayService.updateUserBalance(usersBalance);

        isRun = false;
        logger.info("End fee_rate day task");
    }

}
