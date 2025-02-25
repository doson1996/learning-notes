package com.ds.dp.strategy.example;

/**
 * @Author ds
 * @Date 2021/3/31 10:08
 * @Description 扩展银行卡支付
 */
public class PaymentContext1 extends PaymentContext {

    private String account;

    public PaymentContext1(String name, double money, PaymentStrategy strategy, String account) {
        super(name, money, strategy);
        this.account = account;
    }

    public String getAccount() {
        return account;
    }

}
