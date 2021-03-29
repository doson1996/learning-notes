package com.ds.concurrent.chapter01;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * @Author ds
 * @Date 2021/3/29 15:49
 * @Description Semaphore往往⽤于资源有限的场景中，去限制线程的数量
 */
public class Demo15Semaphore {

    public static void main(String[] args) {

        Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < 10; i++) {
            new Thread(new ThreadA(semaphore, "Thread" + i)).start();
           /* try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        }

    }

    private static class ThreadA implements Runnable{

        private Semaphore semaphore;

        private String name;

        public ThreadA(Semaphore semaphore, String name) {
            this.semaphore = semaphore;
            this.name = name;
        }

        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println(String.format("当前线程--%s, 还剩%d个资源", name,semaphore.availablePermits()));

                Random random = new Random();
                int ms = random.nextInt(1000);
                Thread.sleep(ms);
                semaphore.release();
                System.out.println(String.format("线程--%s释放了资源",name));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
