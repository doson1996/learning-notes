package com.ds.netty.chapter02netty.pipeline;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;

/**
 * @author ds
 * @date 2023/6/6
 * @description
 */
@Slf4j
public class Server {
    public static void main(String[] args) {
        new ServerBootstrap()
                .group(new NioEventLoopGroup())
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        // 1.通过channel拿到pipeline
                        ChannelPipeline pipeline = ch.pipeline();
                        // 2.添加处理器 head -> handler1 -> tail
                        pipeline.addLast("handler1", new ChannelInboundHandlerAdapter() {
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                log.info("handler1 channelRead...");

                                ByteBuf buf = (ByteBuf) msg;
                                String name = buf.toString(Charset.defaultCharset());
                                // 调用下一个handler
                                super.channelRead(ctx, name);
                            }
                        });
                        // head -> handler1 -> handler2 -> tail
                        pipeline.addLast("handler2", new ChannelInboundHandlerAdapter() {
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object name) throws Exception {
                                log.info("handler2 channelRead...{}", name);
                                User user = new User((String) name);
                                // 调用下一个handler
                                ctx.fireChannelRead(user);
                            }
                        });
                        // head -> handler1 -> handler2 -> handler3 -> tail
                        pipeline.addLast("handler3", new ChannelInboundHandlerAdapter() {
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object user) throws Exception {
                                log.info("handler3 channelRead...{}", user);
                                // 向channel写数据，才会调用 handler4、5、6
                                ch.writeAndFlush(ctx.alloc().buffer().writeBytes("hello".getBytes()));
                            }
                        });
                        // head -> handler1 -> handler2 -> handler3 -> handler4 -> tail
                        pipeline.addLast("handler4", new ChannelOutboundHandlerAdapter() {
                            @Override
                            public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
                                log.info("handler4 write...");
                                super.write(ctx, msg, promise);
                            }
                        });
                        // head -> handler1 -> handler2 -> handler3 -> handler4 -> handler5 -> tail
                        pipeline.addLast("handler5", new ChannelOutboundHandlerAdapter() {
                            @Override
                            public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
                                log.info("handler5 write...");
                                super.write(ctx, msg, promise);
                            }
                        });
                        // head -> handler1 -> handler2 -> handler3 -> handler4 -> handler5 -> handler6 -> tail
                        pipeline.addLast("handler6", new ChannelOutboundHandlerAdapter() {
                            @Override
                            public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
                                log.info("handler6 write...");
                                super.write(ctx, msg, promise);
                            }
                        });
                    }
                })
                .bind(8080);
    }
}
