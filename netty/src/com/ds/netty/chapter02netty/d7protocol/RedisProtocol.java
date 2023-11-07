package com.ds.netty.chapter02netty.d7protocol;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * @author ds
 * @date 2023/6/26
 * @description <参数数量> CR LF
 * $<参数 1 的字节数量> CR LF
 * <参数 1 的数据> CR LF
 * ...
 * $<参数 N 的字节数量> CR LF
 * <参数 N 的数据> CR LF
 * // 该指令一共有3部分，每条指令之后都要添加回车与换行符
 * *3\r\n
 * // 第一个指令的长度是3
 * $3\r\n
 * // 第一个指令是set指令
 * set\r\n
 * // 下面的指令以此类推
 * $4\r\n
 * name\r\n
 * $5\r\n
 * Nyima\r\n
 */
@Slf4j
public class RedisProtocol {

    /**
     * 换行
     */
    private static final String LINE_BREAK = "\r\n";

    public static void main(String[] args) throws Exception {

        new Bootstrap()
                .group(new NioEventLoopGroup(2))
                .channel(NioSocketChannel.class)
                .handler(new ChannelInboundHandlerAdapter() {
                    @Override
                    public void channelActive(ChannelHandlerContext ctx) throws Exception {
                        ByteBuf buffer = ctx.alloc().buffer();
                        buffer.writeBytes(setCommand("name", "zhangsan").getBytes());
                        ctx.writeAndFlush(buffer);
                    }

                    @Override
                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                        ByteBuf buffer = (ByteBuf) msg;
                        log.info("redis server 返回：{}", buffer.toString(Charset.defaultCharset()));
                    }
                })
                .connect(new InetSocketAddress("ds.com", 6378))
                .sync();
    }

    public static String setCommand(String key, String value) {
        StringBuilder command = new StringBuilder();
        command.append("");
        return "*3" + LINE_BREAK +
                "$3" + LINE_BREAK +
                "set" + LINE_BREAK +
                "$" + key.length() + LINE_BREAK +
                key + LINE_BREAK +
                "$" + value.length() + LINE_BREAK +
                value + LINE_BREAK;

    }

}
