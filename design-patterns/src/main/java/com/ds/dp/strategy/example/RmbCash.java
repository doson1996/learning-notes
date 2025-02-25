package com.ds.dp.strategy.example;

/**
 * @Author ds
 * @Date 2021/3/31 10:08
 * @Description
 */
public class RmbCash implements PaymentStrategy {

    @Override
    public void pay(PaymentContext context) {
        System.out.println("给" + context.getName() + "发" + context.getMoney() + "元人民币现金工资");
    }
}
