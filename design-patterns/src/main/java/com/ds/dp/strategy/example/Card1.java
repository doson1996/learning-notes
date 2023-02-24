package com.ds.dp.strategy.example;

/**
 * @Author ds
 * @Date 2021/3/31 10:21
 * @Description 支付到银行卡 第二种扩展
 */
public class Card1 implements PaymentStrategy{

    private String account;

    public Card1(String account) {
        this.account = account;
    }

    @Override
    public void pay(PaymentContext context) {
        System.out.println("给" + context.getName() + "发" + context.getMoney() + "到" + this.account);
    }
}
