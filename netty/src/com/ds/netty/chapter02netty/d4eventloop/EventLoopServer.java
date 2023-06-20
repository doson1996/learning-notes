package com.ds.netty.chapter02netty.d4eventloop;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;

/**
 * @author ds
 * @date 2023/5/29
 * @description
 */
@Slf4j
public class EventLoopServer {
    public static void main(String[] args) {
        // 独立创建一个 EventLoopGroup来执行任务
        EventLoopGroup group = new DefaultEventLoopGroup(2);

        new ServerBootstrap()
                // parentGroup只负责ServerSocketChannel accept事件
                // childGroup只负责SocketChannel 读写事件
                .group(new NioEventLoopGroup(), new NioEventLoopGroup(2))
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast("handler1", new ChannelInboundHandlerAdapter() {
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                ByteBuf buf = (ByteBuf) msg;
                                String recvMsg = buf.toString(StandardCharsets.UTF_8);
                                log.info("服务器接收到消息: {}, handler1处理...", recvMsg);
                                if ("2".equals(recvMsg))
                                    ctx.fireChannelRead(msg); // 消息传递给下一个handler
                            }
                        });

                        pipeline.addLast(group, "handler2", new ChannelInboundHandlerAdapter() {
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                ByteBuf buf = (ByteBuf) msg;
                                log.info("服务器接收到消息: {}, handler2处理...", buf.toString(StandardCharsets.UTF_8));
                            }
                        });
                    }
                })
                .bind(8080);
    }
}
