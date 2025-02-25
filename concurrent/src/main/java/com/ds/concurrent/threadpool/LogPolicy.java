package com.ds.concurrent.threadpool;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author ds
 * @date 2024/4/10
 * @description 拒绝策略-记录日志
 */
public class LogPolicy implements RejectedExecutionHandler {

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println("Task:" + r);
    }

}
