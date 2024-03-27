package com.app.dc.entity;

import lombok.Data;

/**
 * @Description
 * @Author
 * @Date
 **/
@Data
public class DepositionEvent {

    private String txHash;
    private long blockNumber;
    private long blockTime;
    private String token;
    private String account;
    private String amount;
    private int chainId;

}

