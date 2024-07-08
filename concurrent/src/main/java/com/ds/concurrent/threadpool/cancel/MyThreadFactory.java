package com.ds.concurrent.threadpool.cancel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author ds
 * @Date 2021/3/29 17:24
 * @Description
 */
public class MyThreadFactory implements ThreadFactory {

    private final AtomicInteger id = new AtomicInteger(0);

    private final String name;

    public MyThreadFactory(String name) {
        this.name = name;
    }

    private List<Thread> threadList = new ArrayList<>();

    @Override
    public Thread newThread(Runnable task) {
        String threadName = name + "-" + id.getAndDecrement();
        Thread thread = new Thread(task, threadName);
//        thread.setDaemon(true);
        System.out.println("新建线程: " + threadName);
        threadList.add(thread);
        return thread;
    }

    public List<Thread> getThreadList() {
        return threadList;
    }
}
