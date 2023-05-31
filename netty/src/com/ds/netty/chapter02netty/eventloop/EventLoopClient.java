package com.ds.netty.chapter02netty.eventloop;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;

/**
 * @author ds
 * @date 2023/5/29
 * @description
 */
@Slf4j
public class EventLoopClient {
    public static void main(String[] args) throws InterruptedException {
        ChannelFuture channelFuture = new Bootstrap()
                .group(new NioEventLoopGroup())
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new StringEncoder());
                    }
                })
                .connect(new InetSocketAddress("localhost", 8080));
        // 1. 使用sync方法，同步处理结果
//        channelFuture.sync();
//        Channel channel = channelFuture.channel();
//        log.info("channel = {}", channel);
//        channel.writeAndFlush("aa");

        // 2. 使用addListener(回调对象)方法异步处理结果
        channelFuture.addListener((ChannelFutureListener) future -> {
            Channel channel = future.channel();
            log.info("channel = {}", channel);
            channel.writeAndFlush("listener异步执行");
        });
    }
}
