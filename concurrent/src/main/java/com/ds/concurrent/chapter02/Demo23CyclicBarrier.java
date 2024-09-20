package com.ds.concurrent.chapter02;

import java.util.concurrent.CyclicBarrier;

/**
 * @Author ds
 * @Date 2021/4/19 17:20
 * @Description 同步屏障 CyclicBarrier
 * CyclicBarrier 的字面意思是可循环使用（Cyclic）的屏障（Barrier）。它要做的事情是，让一组线程到达一个屏障
 * （也可以叫同步点）时被阻塞，直到最后一个线程到达屏障时，屏障才会开门，所有被屏障拦截的线程才会继续运行。
 */
public class Demo23CyclicBarrier {

    /**
     * CyclicBarrier 默认的构造方法是 CyclicBarrier（int parties），其参数表示屏障拦截的
     * 线程数量，每个线程调用 await 方法告诉 CyclicBarrier 我已经到达了屏障，然后当前线程被阻塞。
     */
    //static CyclicBarrier c = new CyclicBarrier(2);

    /**
     * CyclicBarrier 还提供一个更高级的构造函数 CyclicBarrier（int parties， Runnablebarrier-Action），
     * 用于在线程到达屏障时，优先执行 barrierAction，方便处理更复杂的业务场景
     */
    static CyclicBarrier c = new CyclicBarrier(2, new TaskA());

    public static void main(String[] args) throws Exception {
        new Thread(() -> {
            System.out.println("1- " + System.currentTimeMillis());
            try {
                c.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        c.await();
        System.out.println("2- " + System.currentTimeMillis());

    }

    private static class TaskA implements Runnable {

        @Override
        public void run() {
            System.out.println("TaskA 优先执行- " + System.currentTimeMillis());
        }
    }


}
