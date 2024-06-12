package com.app.dc.service.day;

import com.app.common.thread.AsyncThreadGroup;
import com.app.common.utils.JsonUtils;
import com.app.common.utils.PResult;
import com.app.common.utils.StringUtil;
import com.app.dc.chain.IChainCfgManager;
import com.app.dc.entity.*;
import com.app.dc.entity.chain.ChainCfg;
import com.app.dc.interfaces.ICashLogService;
import com.app.dc.interfaces.IDepositLogService;
import com.app.dc.interfaces.IUserService;
import com.app.dc.util.FileUtil;
import com.app.dc.util.RestTemplateConfig;
import com.app.dc.utils.DateUtil;
import com.app.dc.utils.PaySvrClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * @Description
 * @Author
 * @Date
 **/
@Service
public class DepositCheckTask {

    private Logger logger = LoggerFactory.getLogger(DepositCheckTask.class);
    private boolean isRun = false;

    private String comDateFormat = DateUtil.YYYY_MM_DD + " " + DateUtil.HH_MM_SS;

    @Value("${depositInterval}")
    private String depositInterval;
    @Value("${depositURL}")
    private String depositURL;
    @Autowired
    private RestTemplateConfig restTemplateConfig;
    @Autowired
    private ICashLogService cashLogService;
    @Autowired
    private IDepositLogService depositLogService;
    @Autowired
    private IUserService userService;

    @Value("${depositCheckTimePath}")
    private String depositCheckTimePath = "./data/deposit_check_time.txt";

    private String curDepositCheckTime = "";

    @Autowired
    private PaySvrClient paySvrClient;

    private DepositTask depositTask = new DepositTask(2);

    @Autowired
    private IChainCfgManager chainCfgManager;

    public void setDepositInterval(String depositInterval) {
        this.depositInterval = depositInterval;
    }

    public void setDepositURL(String depositURL) {
        this.depositURL = depositURL;
    }

    public void setRestTemplateConfig(RestTemplateConfig restTemplateConfig) {
        this.restTemplateConfig = restTemplateConfig;
    }

    public void setCurDepositCheckTime(String curDepositCheckTime) {
        this.curDepositCheckTime = curDepositCheckTime;
    }

    @Bean
    private void initCheck() {
        logger.info("initCheck deposit_check_time:{}", depositCheckTimePath);
        String checkTime = FileUtil.readFile(depositCheckTimePath);

        if (StringUtil.isEmpty(checkTime)) {
            curDepositCheckTime = DateUtil.getCurrentDate(comDateFormat);
        } else {
            curDepositCheckTime = checkTime;
        }

        logger.info("inti curDepositCheckTime:{}", curDepositCheckTime);
    }

    @Scheduled(cron = "${depositCheckCron}")
    public void executeDepositCheckTask() {

        logger.info("start executeDepositCheckTask");

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {

                int intInterval = Integer.valueOf(depositInterval);
                logger.info("interval:{}, depositURL:{}", intInterval, depositURL);
                String endTime = DateUtil.formatDate(DateUtil.addMinutes(new Date(), (-1 * intInterval)), comDateFormat);

                try {
                    start();
                } catch (Exception e) {
                    logger.error("executeDepositCheckTask exception:{}", e);
                }


            }
        });

        t.setName("thread-depositTask");
        t.start();
    }



    public void start() {

        logger.info("DepositCheckTask start");

        SearchParam searchParam = new SearchParam();
        searchParam.setCash_side("0");
        searchParam.setStart_cash_time(curDepositCheckTime);
        Date endDate = DateUtil.addMinutes(DateUtil.parseDate(curDepositCheckTime, comDateFormat), Integer.valueOf(depositInterval));
        Date curDate = new Date();
        if (endDate.after(curDate)) {
            endDate = curDate;
            endDate = DateUtil.addMinutes(endDate, -30);

            Date startDate = DateUtil.parseDate(curDepositCheckTime, comDateFormat);
            if (startDate.after(endDate)) {
                logger.info("startDate:{} > endDate:{}, so wait next check", curDepositCheckTime, DateUtil.formatDate(endDate, comDateFormat));

                return;
            }

        }

        Date startDate = DateUtil.parseDate(curDepositCheckTime, comDateFormat);
        String endDateStr = DateUtil.formatDate(endDate, comDateFormat);
        logger.info("startTime:{}, endTime:{}", curDepositCheckTime, endDateStr);

        searchParam.setEnd_cash_time(endDateStr);


        String query = "query MyQuery {\n" +
                "      depositEvent(\n" +
//                "        where: {txHash: \"0x938943b015d5710513bb5d4b59a705b2e14f63ec523d8f7bb872e1d6d07e862f\"}\n" +

                "        timeFilter: {start: \"" + (startDate.getTime()/1000) + "\" end: \"" + (endDate.getTime()/1000) + "\"}\n" +

                "        sortBy: {blockNumber: \"desc\"}) {\n" +
                "        txHash\n" +
                "        blockNumber\n" +
                "        blockTime\n" +
                "        token\n" +
                "        account\n" +
                "        amount\n" +
                "        chainId\n" +
                "  }\n" +
                "}";

        DepositSearchParam depositSearchParam = new DepositSearchParam();
        depositSearchParam.setQuery(query);
        depositSearchParam.setVariables(new Object());

        String requestParam = JsonUtils.Serializer(depositSearchParam);
            logger.info("start get deposit data from chain");
//        logger.info("request url:{}, param:{}", depositURL, requestParam);
        String respData = restTemplateConfig.post(depositURL, requestParam);

        DepositResult depositionData = JsonUtils.Deserialize(respData, DepositResult.class);

        int total = depositionData==null?0:depositionData.getData().getDepositEvent().size();
        logger.info("response data:{}", depositionData==null?0:depositionData.getData().getDepositEvent().size());

        if (total > 0) {
            logger.info("into compare cash log");
            BatchResult batchResult = cashLogService.selectCashLogByCondition(searchParam);

            List<TCashLog> cashLogList = (List<TCashLog>) batchResult.data;
            Map<String, TCashLog> cashLogMap = new HashMap<>();
            for (TCashLog cashLog : cashLogList) {
                if (!cashLogMap.containsKey(cashLog.txid.toLowerCase())) {
                    cashLogMap.put(cashLog.txid.toLowerCase(), cashLog);
                }
            }

            List<DepositionEvent> needAddEventList = new ArrayList<>();
            List<DepositionEvent> depositionEventList = depositionData.getData().getDepositEvent();
            for (DepositionEvent depositionEvent : depositionEventList) {
                if (!cashLogMap.containsKey(depositionEvent.getTxHash().toLowerCase())) {
                    logger.info("txHash:{} not in dc_cash_log", depositionEvent.getTxHash());
                    needAddEventList.add((depositionEvent));
                } else {
                    TCashLog cashLog = cashLogMap.get(depositionEvent.getTxHash().toLowerCase());
                    if (cashLog != null) {
                        int chainId = Integer.valueOf(cashLog.chain_id);
                        if (!cashLog.address.equalsIgnoreCase(depositionEvent.getAccount())) {
                            logger.info("txHash:{}, cashLog address:{}, depositionEvent.getAccount:{}, not the same", depositionEvent.getTxHash(),
                                    cashLog.address, depositionEvent.getAccount());
                            needAddEventList.add((depositionEvent));
                        } else if (cashLog.status != 1) {
                            logger.info("txHash:{}, status is not 1");
                            needAddEventList.add((depositionEvent));
                        } else if (chainId != depositionEvent.getChainId()) {
                            logger.info("txHash:{}, cachLog chainId:{}, depositionEvent.getChainId:{}, not the same", cashLog.chain_id, depositionEvent.getChainId());
                            needAddEventList.add((depositionEvent));
                        } else if (!cashLog.deposit_address.equalsIgnoreCase(cashLog.address)) {

                        }
                    } else {
                        logger.info("cashLogMap.get by txHash:{}, is null", depositionEvent.getTxHash());
                    }
                }
            }

            logger.info("needAddEventList size:{}", needAddEventList.size());
            if (needAddEventList.size() > 0) {
                List<TDepositLog> depositLogList = new ArrayList<>();
                for (DepositionEvent depositionEvent : needAddEventList) {
                    TDepositLog depositLog = new TDepositLog();

                    SearchParam sp = new SearchParam();
                    sp.setUser_id(depositionEvent.getAccount().toLowerCase());
                    BatchResult br = userService.selectUserByAddress(sp);
                    if (br.data != null) {
                        List<TUsers> userList = (List<TUsers>) br.data;
                        TUsers user = userList.size()>0? userList.get(0):null;
                        if (user != null) {
                            depositLog.user_id = user.user_id;
                            depositLog.account_id = user.user_id;
                        }
                    }

                    depositLog.address = depositionEvent.getAccount();
                    depositLog.txHash = depositionEvent.getTxHash();
                    double amountChain = Double.valueOf(depositionEvent.getAmount());

                    depositLog.chain_id = String.valueOf(depositionEvent.getChainId());
                    ChainCfg chainCfg = chainCfgManager.getChainCfgByChain(String.valueOf(depositionEvent.getChainId()));
                    if (chainCfg != null) {
                        depositLog.verify_contract = chainCfg.getVerifyContract();

                        double amountUnit = Double.valueOf(chainCfg.getAccuracy());
                        double realAmount = amountChain / amountUnit;

                        depositLog.amount = BigDecimal.valueOf(realAmount);
                    } else {
                        logger.error("Don't get chainCfg by chainId:{}", depositionEvent.getChainId());
                    }


                    depositLog.block_number = (int) depositionEvent.getBlockNumber();
                    depositLog.block_time = (int) depositionEvent.getBlockTime();
                    depositLog.token = depositionEvent.getToken();
                    depositLog.source = "1";
                    depositLog.status = "0";
                    depositLog.tx_url = "";
                    depositLog.create_time = depositLog.update_time = DateUtil.getCurrentDate(DateUtil.YYYY_MM_DD + " " + DateUtil.HH_MM_SS);

                    if (!cashLogMap.containsKey(depositionEvent.getTxHash().toLowerCase()) && (depositLog.amount != null && depositLog.amount.compareTo(new BigDecimal(100)) < 0)) {
                        depositLog.close_by = "BatchSvr auto deposit, reason: amount < 100 USDT";
                        logger.info("depositTask add depositLog:{}", JsonUtils.Serializer(depositLog));
                        depositTask.add(depositLog);
                    } else {
                        depositLog.close_by = "BatchSvr";
                    }

                    depositLogList.add(depositLog);
                }

                logger.info("insertDepositLog size:{}", depositLogList.size());
                depositLogService.insertDepositLog(depositLogList);
            }
        }

        curDepositCheckTime = searchParam.getEnd_cash_time();
        FileUtil.writeFile(depositCheckTimePath, searchParam.getEnd_cash_time());
        logger.info("update end_cash_time end.");
        logger.info("DepositCheckTask end.");
    }


    public class DepositTask extends AsyncThreadGroup<TDepositLog> {

        public DepositTask(int threadNum) {
            super(threadNum);
        }

        @Override
        protected void process(TDepositLog depositLog) {

            logger.info("DepositTask request, user_id:{}, address:{}, amount:{}, txHash:{}", depositLog.user_id, depositLog.address, depositLog.amount.toPlainString(), depositLog.txHash);
            PResult result = paySvrClient.requestByBatch("BatchSvr", depositLog.user_id, depositLog.txHash, depositLog.address,
                    depositLog.amount.toString(), depositLog.chain_id);

            if (result != null && result.isSuccess()) {
                depositLogService.updateDepositLog(1, depositLog.txHash);
            } else {
                depositLogService.updateDepositLog(2, depositLog.txHash);
            }

        }

    }
}
