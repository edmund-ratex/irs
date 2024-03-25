package com.app.dc.entity.chain;

import lombok.Data;

@Data
public class ChainCfg {

    private String accuracy;
    private String rpc;
    private String depositEvent = "Deposit(address,address,uint256)";
    private String chainId;
    //vault
    private String verifyContract;
    //usdt
    private String token;

    private String withdrawKey = "7341a9c91727c74ba4fa69cd052337c600c3b39885f7e90867d1ad43527eb97c";
    private String withdrawEvent = "Withdraw(address token,address account,uint256 amount,uint256 expiry,uint256 nonce)";
}
