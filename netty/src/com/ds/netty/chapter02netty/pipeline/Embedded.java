package com.ds.netty.chapter02netty.pipeline;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.embedded.EmbeddedChannel;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;

/**
 * @author ds
 * @date 2023/6/6
 * @description 测试channel
 */
@Slf4j
public class Embedded {
    public static void main(String[] args) {
        ChannelInboundHandlerAdapter h1 = new ChannelInboundHandlerAdapter() {
            @Override
            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                log.info("handler1 channelRead...");

                ByteBuf buf = (ByteBuf) msg;
                String name = buf.toString(Charset.defaultCharset());
                // 调用下一个handler
                super.channelRead(ctx, name);
            }
        };

        ChannelInboundHandlerAdapter h2 = new ChannelInboundHandlerAdapter() {
            @Override
            public void channelRead(ChannelHandlerContext ctx, Object name) throws Exception {
                log.info("handler2 channelRead...{}", name);
                User user = new User((String) name);
                // 调用下一个handler
                ctx.fireChannelRead(user);
            }
        };

        ChannelOutboundHandlerAdapter h3 = new ChannelOutboundHandlerAdapter() {
            @Override
            public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
                log.info("handler3 write...");
                super.write(ctx, msg, promise);
            }
        };

        ChannelOutboundHandlerAdapter h4 = new ChannelOutboundHandlerAdapter() {
            @Override
            public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
                log.info("handler4 write...");
                super.write(ctx, msg, promise);
            }
        };

        EmbeddedChannel channel = new EmbeddedChannel(h1, h2, h3, h4);
        // 1.模拟入站
         channel.writeInbound(ByteBufAllocator.DEFAULT.buffer().writeBytes("张三".getBytes()));

        // 2.模拟出站
        channel.writeOutbound(ByteBufAllocator.DEFAULT.buffer().writeBytes("张三".getBytes()));
    }
}
