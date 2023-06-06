package com.ds.netty.chapter02netty.future;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @author ds
 * @date 2023/6/6
 * @description
 */
@Slf4j
public class JdkFuture {

    private static final ExecutorService threadPool = Executors.newFixedThreadPool(1);

    public static void main(String[] args) throws Exception {
        Future<String> future = threadPool.submit(new Task());
        log.info("等待返回...");
        String result = future.get();
        log.info("返回 {}", result);
    }

}
