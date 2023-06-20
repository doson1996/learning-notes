package com.ds.netty.chapter02netty.d1hello;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;

/**
 * @author ds
 * @date 2023/5/25
 * @description
 */
public class Client {
    public static void main(String[] args) throws Exception {
        // 启动器
        new Bootstrap()
                // 添加EventLoop
                .group(new NioEventLoopGroup())
                // 选择客户端 channel 实现
                .channel(NioSocketChannel.class)
                // 添加处理器
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    // 连接后调用
                    @Override
                    protected void initChannel(NioSocketChannel channel) throws Exception {
                        channel.pipeline().addLast(new StringEncoder());
                    }
                })
                .connect(new InetSocketAddress("localhost", 8080))
                .sync()
                .channel()
                // 向服务器发送数据
                .writeAndFlush("hello netty");
    }
}
