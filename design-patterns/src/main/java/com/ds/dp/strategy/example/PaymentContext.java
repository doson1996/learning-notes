package com.ds.dp.strategy.example;

/**
 * @Author ds
 * @Date 2021/3/31 10:08
 * @Description
 */
public class PaymentContext {

    private String name;

    private double money;

    private PaymentStrategy strategy;

    public PaymentContext(String name, double money, PaymentStrategy strategy) {
        this.name = name;
        this.money = money;
        this.strategy = strategy;
    }

    public String getName() {
        return name;
    }

    public double getMoney() {
        return money;
    }

    /**
     * 立即支付工资
     */
    public void payNow(){
        strategy.pay(this);
    }
}
