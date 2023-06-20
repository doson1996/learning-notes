package com.ds.netty.chapter02netty.d3future;

import io.netty.channel.EventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.Future;
import lombok.extern.slf4j.Slf4j;

/**
 * @author ds
 * @date 2023/6/6
 * @description
 */
@Slf4j
public class NettyFuture {
    public static void main(String[] args) throws Exception {
        NioEventLoopGroup group = new NioEventLoopGroup();
        EventLoop eventLoop = group.next();
        Future<String> future = eventLoop.submit(new Task());
        log.info("等待返回...");

        // 1.异步
        future.addListener((f) -> {
            log.info("异步返回 {}", f.get());
        });

        // 2.同步
        log.info("同步返回 {}", future.get());
    }
}
