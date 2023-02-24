package com.ds.concurrent.chapter02;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author ds
 * @Date 2021/4/15 17:03
 * @Description
 *             在并发编程中使用 HashMap 可能导致程序死循环。而使用线程安全的 HashTable效率又非常低下
 */
public class Demo15ConcurrentHashMap {

    public static void main(String[] args) throws InterruptedException {
        ConcurrentHashMap map = new ConcurrentHashMap(13);
    }

    /**
     * jdk7 死锁
     * @throws InterruptedException
     */
    public void jdk7() throws InterruptedException {
        final HashMap<String, String> map = new HashMap<>(2);
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            map.put(UUID.randomUUID().toString(), "");
                        }
                    }, "ftf" + i).start();
                }
            }
        }, "ftf");
        t.start();
        t.join();
    }

    /**
     * jdk8 可能导致死循环
     */
    public void jdk8() {
        Map<String,String> map = new HashMap<>();
        for (int i = 0;i < 500;i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0;j < 500;j++) {
                        map.put(Thread.currentThread().getName() + "-" + j, j+"");
                    }
                }
            }).start();
        }
        try {
            Thread.sleep(2000);
//            map.forEach((x,y) -> System.out.println(x));
            System.out.println(map.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
