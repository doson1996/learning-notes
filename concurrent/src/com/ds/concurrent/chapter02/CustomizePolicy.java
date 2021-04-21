package com.ds.concurrent.chapter02;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author ds
 * @Date 2021/4/21 14:23
 * @Description 自定义拒绝策略
 */
public class CustomizePolicy implements RejectedExecutionHandler {

    public CustomizePolicy() { }

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        // 可以记录日志或存入数据库
       /* System.out.println("Task " + r.toString() +
                " rejected from " +
                executor.toString());*/
    }
}
