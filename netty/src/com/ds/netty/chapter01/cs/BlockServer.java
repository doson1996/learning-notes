package com.ds.netty.chapter01.cs;

import com.ds.netty.chapter01.BufferUtils;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author ds
 * @date 2023/5/5
 * @description 阻塞服务器
 */
@Slf4j
public class BlockServer {
    public static void main(String[] args) throws Exception {
        ServerSocketChannel server = ServerSocketChannel.open();
        server.bind(new InetSocketAddress(8080));
        log.info("服务器已启动 ->{}...", server);
        while (true) {
            SocketChannel channel = server.accept();
            log.info("连接已创建 ->{}...", channel);
            ByteBuffer buffer = ByteBuffer.allocate(10);
            log.info("读取之前...");
            channel.read(buffer);
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
