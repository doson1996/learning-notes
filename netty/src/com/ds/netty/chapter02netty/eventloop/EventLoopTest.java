package com.ds.netty.chapter02netty.eventloop;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.NettyRuntime;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author ds
 * @date 2023/5/28
 * @description
 */
@Slf4j
public class EventLoopTest {
    public static void main(String[] args) {
        EventLoopGroup group = new NioEventLoopGroup(2); // io事件，普通任务，定时任务
        log.info("{}", NettyRuntime.availableProcessors());

        // 获取下一个事件循环对象
        System.out.println(group.next());
        System.out.println(group.next());
        System.out.println(group.next());
        System.out.println(group.next());

        // 执行普通任务
        group.next().execute(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            log.info("task...");
        });

        // 执行定时任务
        group.next().scheduleWithFixedDelay(() -> log.info("scheduleWithFixedDelay task..."), 2L, 1L, TimeUnit.SECONDS);
        //group.next().scheduleAtFixedRate(() -> log.info("scheduleAtFixedRate task..."), 2L, 1L, TimeUnit.SECONDS);

        log.info("main...");
    }
}
