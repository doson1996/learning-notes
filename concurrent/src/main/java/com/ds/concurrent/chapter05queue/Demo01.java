package com.ds.concurrent.chapter05queue;

import com.ds.concurrent.util.SleepUtils;

import java.util.Comparator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author ds
 * @date 2024/1/5
 * @description
 */
public class Demo01 {

    private static final BlockingQueue QUEUE = new PriorityBlockingQueue<>(10, new Comparator<Task>() {
        @Override
        public int compare(Task o1, Task o2) {
            return o1.level - o2.level;
        }
    });

    private static final ThreadPoolExecutor THREAD_POOL = new ThreadPoolExecutor(
            2, 4,
            10L,
            TimeUnit.SECONDS,
            QUEUE,
            new ThreadPoolExecutor.DiscardPolicy());

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 100; i++) {
            THREAD_POOL.execute(new Task(i % 10));
        }

        while (!THREAD_POOL.isShutdown()) {
            if (QUEUE.isEmpty()) {
                THREAD_POOL.shutdown();
                System.out.println("close...");
            }
        }
    }

    static class Task implements Runnable {

        private final int level;

        public Task(int level) {
            this.level = level;
        }

        @Override
        public void run() {
            System.out.println(level + " exec...");
            SleepUtils.milliseconds(20);
        }
    }
}
