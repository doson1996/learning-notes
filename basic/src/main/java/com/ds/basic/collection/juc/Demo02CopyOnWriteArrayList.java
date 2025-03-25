package com.ds.basic.collection.juc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author ds
 * @date 2025/3/16
 * @description
 */
public class Demo02CopyOnWriteArrayList {
    public static void main(String[] args) {
        CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>();
//        List<Integer> list = new ArrayList<>();
//        list = new CopyOnWriteArrayList<>();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                list.add(i);
                System.out.println(Thread.currentThread().getName() + " i = " + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("list = " + list);
                try {
                    Thread.sleep(110);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 10; i < 20; i++) {
                list.add(i);
                System.out.println(Thread.currentThread().getName() + " i = " + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
}
