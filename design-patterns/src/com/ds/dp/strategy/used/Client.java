package com.ds.dp.strategy.used;

/**
 * @Author ds
 * @Date 2021/3/31 9:43
 * @Description 用策略模式重写
 */
public class Client {

    public static void main(String[] args) {
        Strategy strategy = new NewCustomerStrategy();
        Price price = new Price(strategy);
        double quote = price.quote(100);
        System.out.println("quote = " + quote);

    }
}
