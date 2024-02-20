package com.app.dc.chain;

import com.app.dc.entity.chain.ChainCfg;

import java.util.List;

public interface IChainCfgManager {

    void load(String filePath);

    ChainCfg getChainCfgByChain(String chainId);

    List<ChainCfg> getAllChainCfg();

}
