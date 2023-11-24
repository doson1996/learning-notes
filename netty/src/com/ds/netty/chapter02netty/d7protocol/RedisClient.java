package com.ds.netty.chapter02netty.d7protocol;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import static com.ds.netty.chapter02netty.d7protocol.RedisProtocol.setCommand;

/**
 * @author ds
 * @date 2023/6/26
 * @description
 */
@Slf4j
public class RedisClient {
    private Channel client;
    private String response;

    public static void main(String[] args) throws Exception {
        RedisClient redisClient = new RedisClient();
        redisClient.set("user1", "tom");
        redisClient.set("user2", "jack");
    }

    public String set(String key, String value) {
        this.connect();
        ByteBuf buffer = client.alloc().buffer();
        buffer.writeBytes(setCommand(key, value).getBytes());
        client.writeAndFlush(buffer);
        return response;
    }
    @SneakyThrows
    private void connect() {
        if (client != null)
            return;
        client = new Bootstrap()
                .group(new NioEventLoopGroup(2))
                .channel(NioSocketChannel.class)
                .handler(new ChannelInboundHandlerAdapter() {
                    @Override
                    public void channelRead(ChannelHandlerContext ctx, Object msg) {
                        ByteBuf buffer = (ByteBuf) msg;
                        response = buffer.toString(Charset.defaultCharset());
                        log.info("redis server 返回：{}", response);
                    }
                })
                .connect(new InetSocketAddress("ds.com", 6378))
                .sync()
                .channel();
    }

}
