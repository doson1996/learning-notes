package com.ds.concurrent.chapter01;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;

/**
 * @Author ds
 * @Date 2021/3/29 16:21
 * @Description      CyclicBarrier
 *
 */
public class Demo18CyclicBarrier {

    public static void main(String[] args) {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(3,()->{
            System.out.println("开始下一关");
        });

      /*  new Thread(()->{
            try {
                System.out.println("等待数据加载---");
                cyclicBarrier.await();
                System.out.println("数据加载完成---");
            } catch (Exception e) {
                e.printStackTrace();
            }

        }).start();
*/
        new Thread(new PreTaskThread("加载地图", cyclicBarrier)).start();
        new Thread(new PreTaskThread("加载人物", cyclicBarrier)).start();
        new Thread(new PreTaskThread("加载音乐", cyclicBarrier)).start();

    }

    static class PreTaskThread implements Runnable{

        private String task;

        private CyclicBarrier cyclicBarrier;

        public PreTaskThread(String task, CyclicBarrier cyclicBarrier) {
            this.task = task;
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            try {
                for (int i = 1; i < 4; i++) {
                    Random random = new Random();
                    int ms = random.nextInt(1000);
                    Thread.sleep(ms);
                    System.out.println("关卡" + i + "  " + task + " --完成");
                    cyclicBarrier.await();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            cyclicBarrier.reset();
        }
    }
}
