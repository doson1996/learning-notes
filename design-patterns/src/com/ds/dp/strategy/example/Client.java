package com.ds.dp.strategy.example;

/**
 * @Author ds
 * @Date 2021/3/31 10:06
 * @Description
 */
public class Client {

    public static void main(String[] args) {

        PaymentStrategy rmbCash = new RmbCash();
        PaymentContext context1 = new PaymentContext("张三", 10000.0d, rmbCash);
        context1.payNow();

        PaymentStrategy dollarCash = new DollarCash();
        PaymentContext context2 = new PaymentContext("李四", 2000.0d, dollarCash);
        context2.payNow();

        /**
         * 两种扩展方式
         *  context3 扩展上下文，所有策略的实现风格更统一，但是容易形成复杂的上下文层次
         *
         *  context4 在算法策略上添加自己想要的数据，实现起来简单，但是跟其他策略的实现风格不一样
         */
        PaymentStrategy card = new Card();
        PaymentContext context3 = new PaymentContext1("王五", 10000.0d, card, "123456");
        context3.payNow();

        PaymentStrategy card1 = new Card1("123456");
        PaymentContext context4 = new PaymentContext("赵六", 20000.0d, card1);
        context4.payNow();

    }
}
