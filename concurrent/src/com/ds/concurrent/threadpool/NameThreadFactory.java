package com.ds.concurrent.threadpool;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author ds
 * @Date 2021/3/29 17:24
 * @Description
 */
public class NameThreadFactory implements ThreadFactory {

    private final AtomicInteger id = new AtomicInteger(0);

    private String name;

    public NameThreadFactory(String name) {
       /* if (!name.endsWith(".")) {
            name += ".";
        }*/
        this.name = name;
    }

    @Override
    public Thread newThread(Runnable r) {
        String threadName = name + id.getAndDecrement();
        Thread thread = new Thread(r, threadName);
        thread.setDaemon(true);
        return thread;
    }
}
