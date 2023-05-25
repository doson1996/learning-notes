package com.ds.netty.chapter01nio.cs;

import com.ds.netty.chapter01nio.BufferUtils;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ds
 * @date 2023/5/5
 * @description 非阻塞
 */
@Slf4j
public class ServerV1 {
    public static void main(String[] args) throws Exception {
        ServerSocketChannel server = ServerSocketChannel.open();
        server.bind(new InetSocketAddress(8080));
        // 设置为非阻塞模式
        server.configureBlocking(false);
        List<SocketChannel> channels = new ArrayList<>();
        ByteBuffer buffer = ByteBuffer.allocate(10);
        log.info("非阻塞服务器已启动 ->{}...", server);
        while (true) {
            SocketChannel channel = server.accept();
            // 没有建立连接时，返回null
            if (channel == null)
                continue;
            // 设置为非阻塞模式
            channel.configureBlocking(false);
            channels.add(channel);
            for (SocketChannel socketChannel : channels) {
                log.info("连接已创建 ->{}...", socketChannel);
                int read = socketChannel.read(buffer);
                if (read > 0) {
                    log.info("读取之前...");
                    // 切换至读模式
                    buffer.flip();
                    BufferUtils.print(buffer);
                    // 切换至写模式
                    buffer.clear();
                    log.info("读取之后...");
                    //channel.close();
                }
            }
        }
    }
}
