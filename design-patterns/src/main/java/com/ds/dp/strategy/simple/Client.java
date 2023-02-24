package com.ds.dp.strategy.simple;

/**
 * @Author ds
 * @Date 2021/3/30 17:37
 * @Description 策略模式 （本质：分离算法，选择实现）
 *                  优点：
 *                      1.定义一系列算法
 *                      2.避免多重条件语句
 *                  缺点：
 *                      1.必须了解各种策略的不同
 *                      2.增加了对象数目
 *                      3.只适合扁平的算法结构
 */
public class Client {

    public static void main(String[] args) {

        int arg = 1;

        Strategy strategy = new ConcreteStrategyC();
        Context context = new Context(strategy);

        int res = context.contextInterface(arg);
        System.out.println("res = " + res);
    }
}
