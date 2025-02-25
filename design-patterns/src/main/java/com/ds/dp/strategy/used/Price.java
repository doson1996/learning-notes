package com.ds.dp.strategy.used;

/**
 * @Author ds
 * @Date 2021/3/31 9:50
 * @Description
 */
public class Price {

    private Strategy strategy;

    public Price(Strategy strategy) {
        this.strategy = strategy;
    }

    public double quote(double goodsPrice) {
        return strategy.calcPrice(goodsPrice);
    }
}
