package com.ds.netty.chapter02netty.future;

import io.netty.channel.EventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.DefaultPromise;
import io.netty.util.concurrent.Promise;
import lombok.extern.slf4j.Slf4j;

/**
 * @author ds
 * @date 2023/6/6
 * @description
 */
@Slf4j
public class NettyPromise {
    public static void main(String[] args) throws Exception {
        EventLoop eventLoop = new NioEventLoopGroup().next();

        Promise<String> promise = new DefaultPromise<>(eventLoop);

        eventLoop.submit(() -> {
            log.info("执行任务开始...");
            try {
                int i = 1 / 0;
                Thread.sleep(500);
            } catch (Exception e) {
                promise.setFailure(e);
            }
            log.info("执行任务完成...");
            promise.setSuccess("ok");
        });

        log.info("等待返回...");
        String result = promise.get();
        log.info("返回 {}", result);
    }
}
