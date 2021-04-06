package com.ds.concurrent.chapter02.threadpool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Author ds
 * @Date 2021/4/6 14:52
 * @Description
 */
public class DefaultThreadPool<Job extends Runnable> implements ThreadPool{

    /**
     *  线程池最大限制数
     */
    private static final int MAX_WORKER_NUMBERS = 10;

    /**
     *  线程池默认的数量
     */
    private static final int DEFAULT_WORKER_NUMBERS = 5;

    /**
     * 线程池最小的数量
     */
    private static final int MIN_WORKER_NUMBERS = 1;

    /**
     * 这是一个工作列表，将会向里面插入工作
     */
    private final LinkedList<Job> jobs = new LinkedList<Job>();

    /**
     *  工作者列表
     */
    private final List<Worker> workers = Collections.synchronizedList(new ArrayList<Worker>(2));

    /**
     * 工作者线程的数量
     */
    private int workerNum = DEFAULT_WORKER_NUMBERS;

    /**
     *  线程编号生成
     */
    private AtomicLong threadNum = new AtomicLong();

    public DefaultThreadPool(){
        initializeWorkers(DEFAULT_WORKER_NUMBERS);
    }

    public DefaultThreadPool(int num){
        workerNum = num > MAX_WORKER_NUMBERS ? MAX_WORKER_NUMBERS : Math.max(num, MIN_WORKER_NUMBERS);
        initializeWorkers(num);
    }

    @Override
    public void execute(Runnable job) {
        if (job != null) {
            synchronized (jobs){
                jobs.addLast((Job) job);
                jobs.notify();
            }
        }
    }

    @Override
    public void shutdown() {
        for (Worker worker : workers) {
            worker.shutdown();
        }
    }

    @Override
    public void addWorkers(int num) {
        synchronized (jobs) {
            // 限制新增的 Worker 数量不能超过最大值
            if (num + this.workerNum > MAX_WORKER_NUMBERS) {
                num = MAX_WORKER_NUMBERS - this.workerNum;
            }
            initializeWorkers(num);
            this.workerNum += num;
        }

    }

    @Override
    public void removeWorkers(int num) {
        synchronized (jobs) {
            if (num >= this.workerNum) {
                throw new IllegalArgumentException("beyond workNum");
            }
            // 按照给定的数量停止 Worker
            int count = 0;
            while (count < num) {
                Worker worker = workers.get(count);
                if (workers.remove(worker)) {
                    worker.shutdown();
                    count++;
                }
            }
            this.workerNum -= count;
        }
    }

    @Override
    public int getJobSize() {
        return jobs.size();
    }

    private void initializeWorkers(int num){
        for (int i = 0; i < num; i++) {
            Worker worker = new Worker();
            workers.add(worker);
            Thread thread = new Thread(worker,"ThreadPool-Worker-" + threadNum.incrementAndGet());
            thread.start();
        }
    }

    /**
     * 工作者，负责消费任务
     */
    private class Worker implements Runnable{

        /**
         * 是否工作
         */
        private volatile boolean running = true;

        @Override
        public void run() {
            while (running) {
                Job job = null;
                synchronized (jobs) {
                    while (jobs.isEmpty()){
                        try {
                            jobs.wait();
                        } catch (InterruptedException e) {
                            Thread.interrupted();
                            return;
                        }
                    }

                    job = jobs.removeFirst();
                    if (job != null){
                        try {
                            job.run();
                        } catch (Exception e) {
                           //忽略job执行中的异常
                        }
                    }
                }
            }
        }

        public void shutdown() {
            running = false;
        }
    }
}
