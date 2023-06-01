package com.ds.netty.chapter02netty.eventloop;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.util.Scanner;

/**
 * @author ds
 * @date 2023/5/29
 * @description
 */
@Slf4j
public class ChannelCloseClient {
    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup group = new NioEventLoopGroup();
        ChannelFuture channelFuture = new Bootstrap()
                .group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline()
                                .addLast(new LoggingHandler(LogLevel.DEBUG))
                                .addLast(new StringEncoder());
                    }
                })
                .connect(new InetSocketAddress("localhost", 8080));
        channelFuture.sync();
        Channel channel = channelFuture.channel();
        new Thread(()->{
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String input = scanner.nextLine();
                if ("q".equals(input)) {
                    channel.close();
                    break;
                }
                channel.writeAndFlush(input);
            }
        }, "input").start();


        ChannelFuture closeFuture = channel.closeFuture();
        log.info("等待关闭...");
        // 1.同步
//        closeFuture.sync();
//        log.info("关闭完成...");

       // 2.异步
        closeFuture.addListener((ChannelFutureListener) future -> {
            group.shutdownGracefully();
            log.info("关闭完成...");
        });
    }
}
