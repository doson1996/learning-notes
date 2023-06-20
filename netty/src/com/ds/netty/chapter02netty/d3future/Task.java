package com.ds.netty.chapter02netty.d3future;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;

/**
 * @author ds
 * @date 2023/6/6
 * @description
 */
@Slf4j
public class Task implements Callable<String> {
    @Override
    public String call() throws Exception {
        log.info("执行任务开始...");
        Thread.sleep(500);
        log.info("执行任务完成...");
        return "ok";
    }
}
