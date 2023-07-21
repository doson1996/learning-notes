package com.ds.netty.chapter02netty.d9rpc;

import cn.hutool.core.util.IdUtil;
import com.ds.netty.chapter02netty.d8chat.protocol.MessageCodec;
import com.ds.netty.chapter02netty.d8chat.protocol.ProcotolFrameDecoder;
import com.ds.netty.chapter02netty.d9rpc.handler.RpcResponseMessageHandler;
import com.ds.netty.chapter02netty.d9rpc.message.RpcRequestMessage;
import com.ds.netty.chapter02netty.d9rpc.service.HelloService;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Proxy;
import java.util.UUID;

/**
 * @author ds
 * @date 2023/7/20
 * @description
 */
@Slf4j
public class RpcClient {

    private static Channel channel = null;

    public static void main(String[] args) {
        HelloService helloService = getProxyService(HelloService.class);
        helloService.sayHello("zs");
    }

    public static <T> T getProxyService(Class<T> serviceClass) {
        ClassLoader classLoader = serviceClass.getClassLoader();
        Class<?>[] interfaces;
        if (serviceClass.isInterface()) {
            interfaces = new Class[]{serviceClass};
        } else {
            interfaces = serviceClass.getInterfaces();
        }
        return (T) Proxy.newProxyInstance(classLoader, interfaces, (proxy, method, args) -> {
            Class[] parameterTypes = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                parameterTypes[i] = args[i].getClass();
            }

            getChannel().writeAndFlush(new RpcRequestMessage(
                    (int) (System.currentTimeMillis() % 1000000),
                    serviceClass.getName(),
                    method.getName(),
                    String.class,
                    parameterTypes,
                    args
            ));
            return new Object();
        });
    }

    public static Channel getChannel() {
        if (channel != null)
            return channel;
        synchronized (RpcClient.class) {
            if (channel != null)
                return channel;
            initChannel();
            return channel;
        }
    }

    private static void initChannel() {
        NioEventLoopGroup group = new NioEventLoopGroup();
        LoggingHandler LOGGING_HANDLER = new LoggingHandler(LogLevel.DEBUG);
        MessageCodec MESSAGE_CODEC = new MessageCodec(); // 【使用 asm包方法】

        // rpc 响应消息处理器，待实现
        RpcResponseMessageHandler RPC_RESPONSE_HANDLER = new RpcResponseMessageHandler();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.group(group);
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new ProcotolFrameDecoder()); // 【使用 asm包方法】
                    ch.pipeline().addLast(LOGGING_HANDLER);
                    ch.pipeline().addLast(MESSAGE_CODEC);
                    ch.pipeline().addLast(RPC_RESPONSE_HANDLER);
                }
            });
            channel = bootstrap.connect("localhost", 8080).sync().channel();
            /**
             * >>>>>>>>>>>>>>>>>>>>>>>>>>>>
             * channel.closeFuture().sync() 【同步将等待 结束连接才往下执行，将不会返回channel】
             * 改造 ========================
             * 异步监听 指导 结束的动作
             * <<<<<<<<<<<<<<<<<<<<<<<<<<<<
             */
            channel.closeFuture().addListener(promise -> {
                group.shutdownGracefully();
            });
        } catch (Exception e) {
            log.error("client error", e);
        }
    }
}
