package com.nilo.wms.dto.fee;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2017/6/9.
 */
public class FeePrice {

    private BigDecimal firstPrice;

    private BigDecimal nextPrice;

    public BigDecimal getFirstPrice() {
        return firstPrice;
    }

    public void setFirstPrice(BigDecimal firstPrice) {
        this.firstPrice = firstPrice;
    }

    public BigDecimal getNextPrice() {
        return nextPrice;
    }

    public void setNextPrice(BigDecimal nextPrice) {
        this.nextPrice = nextPrice;
    }
}
