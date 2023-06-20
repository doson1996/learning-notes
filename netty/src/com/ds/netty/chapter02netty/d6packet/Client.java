package com.ds.netty.chapter02netty.d6packet;

import com.ds.netty.chapter02netty.d5bytebuf.ByteBufUtils;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;

/**
 * @author ds
 * @date 2023/6/19
 * @description
 */
@Slf4j
public class Client {
    public static void main(String[] args) throws Exception {
        ChannelFuture channelFuture = new Bootstrap()
                .group(new NioEventLoopGroup())
                .channel(NioSocketChannel.class)
                .handler(new ChannelInboundHandlerAdapter() {
                    @Override
                    public void channelActive(ChannelHandlerContext ctx) throws Exception {
                        for (int i = 0; i < 10; i++) {
                            ByteBuf buffer = ctx.alloc().buffer(16);
                            buffer.writeBytes(new byte[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15});
                            ByteBufUtils.log(buffer);
                            ctx.writeAndFlush(buffer);
                        }
                    }
                })
                .connect(new InetSocketAddress("127.0.0.1", 8080))
                .sync();
    }
}
