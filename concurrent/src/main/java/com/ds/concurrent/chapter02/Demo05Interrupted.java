package com.ds.concurrent.chapter02;

import com.ds.concurrent.util.SleepUtils;

/**
 * @Author ds
 * @Date 2021/4/1 17:40
 * @Description
 */
public class Demo05Interrupted {

    public static void main(String[] args) {

        Thread sleepThread = new Thread(() -> {
            while (true) {
                SleepUtils.seconds(10);
            }
        });

        Thread busyThread = new Thread(() -> {
            while (true) {
            }
        });

        sleepThread.setDaemon(true);
        busyThread.setDaemon(true);

        sleepThread.start();
        busyThread.start();

        SleepUtils.seconds(5);

        sleepThread.interrupt();
        busyThread.interrupt();

        System.out.println("sleepThread " + sleepThread.isInterrupted());
        System.out.println("busyThread " + busyThread.isInterrupted());

        // 防止 sleepThread 和 busyThread 立刻退出
        SleepUtils.seconds(2);
    }
}
