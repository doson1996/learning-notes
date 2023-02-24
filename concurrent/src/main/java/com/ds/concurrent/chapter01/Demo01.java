package com.ds.concurrent.chapter01;

import com.ds.concurrent.util.SleepUtils;
import com.ds.concurrent.util.ThreadUtils;

/**
 * @Author ds
 * @Date 2021/4/2 14:26
 * @Description
 */
public class Demo01 {

    static final Object lock = new Object();

    public static void main(String[] args) {
        int size = 10;


        new Thread(()->{
            synchronized (lock) {
                for (int i = 0; i < size; i++) {
                    if (i % 2 == 0){
                        System.out.println(Thread.currentThread().getName() + " " + i);
                        ThreadUtils.notifyAll(lock);
                        ThreadUtils.wait(lock);

                    }
                }
            }
        },"A").start();

        new Thread(()->{
            synchronized (lock) {
                for (int i = 0; i < size; i++) {
                    if (i % 2 != 0){
                        System.out.println(Thread.currentThread().getName() + " " + i);
                        ThreadUtils.notifyAll(lock);
                        ThreadUtils.wait(lock);
                    }
                }
            }
        },"B").start();

    }
}
