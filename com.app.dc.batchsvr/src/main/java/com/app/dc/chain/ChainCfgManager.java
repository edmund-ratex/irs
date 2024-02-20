package com.app.dc.chain;

import com.app.dc.entity.chain.ChainCfg;
import com.app.dc.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;

@Slf4j
@Service
public class ChainCfgManager implements IChainCfgManager {

    private Hashtable<String, ChainCfg> ht = new Hashtable<>();

    @Override
    public void load(String filePath) {

        Properties pc = FileUtil.readProperties(filePath);

        String chainIdStr = pc.getProperty("chainId");
        String[] chainIdArr = chainIdStr.split(",");
        for (String chainId : chainIdArr) {
            ChainCfg chainCfg = new ChainCfg();
            chainCfg.setChainId(chainId);
            chainCfg.setAccuracy(pc.getProperty(chainId + ".accuracy"));
            chainCfg.setDepositEvent(pc.getProperty(chainId + ".depositEvent"));
            chainCfg.setRpc(pc.getProperty(chainId + ".rpc"));
            chainCfg.setToken(pc.getProperty(chainId + ".token"));
            chainCfg.setVerifyContract(pc.getProperty(chainId + ".verifyContract"));
            chainCfg.setWithdrawEvent(pc.getProperty(chainId + ".withdrawEvent"));
            chainCfg.setWithdrawKey(pc.getProperty(chainId + ".withdrawKey"));

            log.info("read cfg:{}", chainCfg.toString());

            if (!ht.containsKey(chainId))
                ht.put(chainId, chainCfg);
        }

    }

    @Override
    public ChainCfg getChainCfgByChain(String chainId) {
        if (ht.containsKey(chainId)) {
            return ht.get(chainId);
        }

        return null;
    }

    @Override
    public List<ChainCfg> getAllChainCfg() {
        List<ChainCfg> result = new ArrayList<>();
        result.addAll(ht.values());

        return result;
    }

}
