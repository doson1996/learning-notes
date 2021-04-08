package com.ds.dp.strategy.example;

/**
 * @Author ds
 * @Date 2021/3/31 10:21
 * @Description
 */
public class Card implements PaymentStrategy{

    @Override
    public void pay(PaymentContext context) {
        PaymentContext1 context1 = (PaymentContext1) context;
        System.out.println("给" + context1.getName() + "发" + context1.getMoney() + "到" + context1.getAccount());
    }
}
