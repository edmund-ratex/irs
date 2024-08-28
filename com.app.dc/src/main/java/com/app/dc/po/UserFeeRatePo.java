package com.app.dc.po;

import com.app.dc.utils.FeeRateUtils;
import lombok.Data;

import java.math.BigDecimal;
import java.util.StringJoiner;

@Data
public class UserFeeRatePo {
    public String sid;
    public String user_id;
    public String symbol;
    public BigDecimal feeRatio;
    public String taker_commission;
    public String maker_commission;

    @Override
    public String toString() {
        return new StringJoiner(", ", UserFeeRatePo.class.getSimpleName() + "[", "]")
                .add("sid='" + sid + "'")
                .add("user_id='" + user_id + "'")
                .add("symbol='" + symbol + "'")
                .add("feeRatio=" + feeRatio)
                .add("taker_commission='" + taker_commission + "'")
                .add("maker_commission='" + maker_commission + "'")
                .toString();
    }
}
