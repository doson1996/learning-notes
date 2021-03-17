package com.ds.dp.observer.example;

import java.util.Observable;
import java.util.Observer;

/**
 * @Author ds
 * @Date 2021/3/17 11:47
 * @Description 观察者模式 （本质：触发联动）
 *              jdk {@link Observable} {@link Observer}
 *              目标和观察者是典型的一对多的关系
 *
 *              优点：
 *                 1.观察者模式实现了观察者与目标之间的抽象耦合
 *                 2.实现了动态联动（一个操作会引起其他相关的操作）
 *                 3.支持广播通信
 *              缺点：
 *                 1.可能会引起无谓的操作（不管观察者需不需要，可能每个观察者都会被调用update方法）
 *
 */
public class Client {

    public static void main(String[] args) {
        Newspaper newspaper = new Newspaper();

        for (int i = 0; i < 10; i++) {
            Reader reader = new Reader();
            reader.setName("读者" + i);
            newspaper.add(reader);
        }

        newspaper.setContent("观察者模式");



    }
}
