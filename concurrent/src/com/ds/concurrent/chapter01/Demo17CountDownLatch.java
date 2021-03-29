package com.ds.concurrent.chapter01;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * @Author ds
 * @Date 2021/3/29 16:09
 * @Description      CountDownLatch
 *
 */
public class Demo17CountDownLatch {

    public static void main(String[] args) {

        CountDownLatch countDownLatch = new CountDownLatch(3);

        new Thread(()->{
            try {
                System.out.println("等待数据加载---");
                System.out.println("还有" + countDownLatch.getCount() + "个任务没加载");
                countDownLatch.await();
                System.out.println("数据加载完成---");
            } catch (Exception e) {
                e.printStackTrace();
            }

        }).start();

        new Thread(new PreTaskThread("加载地图", countDownLatch)).start();
        new Thread(new PreTaskThread("加载人物", countDownLatch)).start();
        new Thread(new PreTaskThread("加载音乐", countDownLatch)).start();

    }

    static class PreTaskThread implements Runnable{

        private String task;

        private CountDownLatch countDownLatch;

        public PreTaskThread(String task, CountDownLatch countDownLatch) {
            this.task = task;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            try {
                Random random = new Random();
                int ms = random.nextInt(1000);
                Thread.sleep(ms);
                System.out.println(task + " --完成");
                countDownLatch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
