package com.ds.concurrent.chapter01;

import java.util.concurrent.Exchanger;

/**
 * @Author ds
 * @Date 2021/3/29 16:01
 * @Description  Exchanger
 *                1.当⼀个线程调⽤exchange⽅法后，它是处于阻塞状态的，只有当另⼀个线程也调⽤了exchange⽅法，
 *                  它才会继续向下执⾏。看源码可以发现它是使⽤ park/unpark来实现等待状态的切换的，但是在使⽤park/unpark⽅法之前，
 *                  使⽤了CAS检查，估计是为了提⾼性能。
 *                2.Exchanger类还有⼀个有超时参数的⽅法，如果在指定时间内没有另⼀个线程调⽤ exchange，就会抛出⼀个超时异常
 *                3.exchange是可以重复使⽤的。也就是说。两个线程可以使⽤Exchanger在内存中不断地再交换数据
 */
public class Demo16Exchanger {

    public static void main(String[] args) {

        Exchanger<String> exchanger = new Exchanger<>();

        new Thread(() -> {
            try {
                System.out.println("线程A---" + exchanger.exchange("这是线程A的数据"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                System.out.println("线程B---" + exchanger.exchange("这是线程B的数据"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }
}
