package com.ds.dp.strategy.example;

/**
 * @Author ds
 * @Date 2021/3/31 10:07
 * @Description 支付工资的策略接口
 */
public interface PaymentStrategy {

    /**
     * 支付工资
     * @param context
     */
    void pay(PaymentContext context);
}
