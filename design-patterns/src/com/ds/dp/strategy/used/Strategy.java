package com.ds.dp.strategy.used;

/**
 * @Author ds
 * @Date 2021/3/31 9:44
 * @Description
 */
public interface Strategy {

    /**
     * 计算价格
     * @param goodsPrice
     * @return
     */
    double calcPrice(double goodsPrice);
}
