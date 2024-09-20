package com.ds.concurrent.chapter02;

import com.ds.concurrent.chapter02.threadpool.DefaultThreadPool;
import com.ds.concurrent.util.ThreadUtils;

import java.util.concurrent.CountDownLatch;

/**
 * @Author ds
 * @Date 2021/4/6 15:27
 * @Description
 */
public class Demo11ThreadPoolTest {

    static CountDownLatch countDownLatch = new CountDownLatch(20);

    public static void main(String[] args) throws InterruptedException {
        DefaultThreadPool pool = new DefaultThreadPool();
        for (int i = 0; i < 20; i++) {
            countDownLatch.countDown();
            Job job = new Job("job-" + i);
            pool.execute(job);
        }
        countDownLatch.await();
        System.out.println("shutdown");
        pool.shutdown();
    }

    private static class Job implements Runnable {

        private String name;

        public Job(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            ThreadUtils.seconds(2);
            System.out.println(name + "running---");
        }
    }
}
