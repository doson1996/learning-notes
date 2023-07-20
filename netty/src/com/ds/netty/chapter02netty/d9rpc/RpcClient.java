package com.ds.netty.chapter02netty.d9rpc;

import com.ds.netty.chapter02netty.d8chat.protocol.MessageCodec;
import com.ds.netty.chapter02netty.d8chat.protocol.ProcotolFrameDecoder;
import com.ds.netty.chapter02netty.d9rpc.handler.RpcResponseMessageHandler;
import com.ds.netty.chapter02netty.d9rpc.message.RpcRequestMessage;
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

/**
 * @author ds
 * @date 2023/7/20
 * @description
 */
@Slf4j
public class RpcClient {
    public static void main(String[] args) {
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
            Channel channel = bootstrap.connect("localhost", 8080).sync().channel();

            final ChannelFuture future = channel.writeAndFlush(
                    new RpcRequestMessage(
                            1,
                            "com.ds.netty.chapter02netty.d9rpc.service.HelloService",
                            "sayHello",
                            String.class,
                            new Class[]{String.class},
                            new Object[]{"zs!"}
                    )
                    // 发送不成功  【打印错误信息】
            ).addListener(promise -> {
                if (!promise.isSuccess()) {
                    Throwable cause = promise.cause();
                    log.error("error : ", cause);
                }
            });
            channel.closeFuture().sync();
        } catch (Exception e) {
            log.error("client error", e);
        } finally {
            group.shutdownGracefully();
        }
    }
}
