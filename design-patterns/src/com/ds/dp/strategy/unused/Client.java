package com.ds.dp.strategy.unused;

/**
 * @Author ds
 * @Date 2021/3/30 17:21
 * @Description
 */
public class Client {

    public static void main(String[] args) {

        double goodsPrice = 100.0d;

        Price price = new Price();
        double quote = price.quote(goodsPrice, CustomerType.OLD_CUSTOMER);
        System.out.println("quote = " + quote);

    }
}
