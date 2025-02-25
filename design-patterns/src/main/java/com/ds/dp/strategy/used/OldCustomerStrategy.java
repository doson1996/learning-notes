package com.ds.dp.strategy.used;

/**
 * @Author ds
 * @Date 2021/3/31 9:47
 * @Description 老客户计算价格策略
 */
public class OldCustomerStrategy implements Strategy {

    @Override
    public double calcPrice(double goodsPrice) {
        return goodsPrice * 0.9d;
    }
}
