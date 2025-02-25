package com.ds.dp.strategy.used;

/**
 * @Author ds
 * @Date 2021/3/31 9:47
 * @Description 大客户计算价格策略
 */
public class ImportantCustomerStrategy implements Strategy {

    @Override
    public double calcPrice(double goodsPrice) {
        return goodsPrice * 0.7d;
    }
}
