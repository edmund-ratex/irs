package com.app.dc;

import com.app.dc.chain.IChainCfgManager;
import com.app.dc.utils.PaySvrClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BatchRun implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IChainCfgManager chainCfgManager;

    @Value("${chainCfgPath}")
    public String chainCfgPath;
    @Autowired
    private PaySvrClient paySvrClient;

    @Override
    public void run(String... args) throws Exception {

        logger.info("init chainCfgManager, chainCfgPath:{}", chainCfgPath);
        chainCfgManager.load(chainCfgPath);
        logger.info("inti paySvrClient");
        paySvrClient.init();
        logger.info("BatchRun end");
    }
}
