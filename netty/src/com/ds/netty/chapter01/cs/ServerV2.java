package com.ds.netty.chapter01.cs;

import com.ds.netty.chapter01.BufferUtils;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * @author ds
 * @date 2023/5/8
 * @description
 */
@Slf4j
public class ServerV2 {
    public static void main(String[] args) throws Exception {
        ServerSocketChannel server = ServerSocketChannel.open();
        // 设置为非阻塞模式
        server.configureBlocking(false);
        // 创建selector，管理多个channel
        Selector selector = Selector.open();
        // 注册selector，将来事件发生后可以通过它知道哪个事件和哪个channel
        // 指定selectionKey只关注accept事件
        SelectionKey selectionKey = server.register(selector, SelectionKey.OP_ACCEPT);
        log.info("selectionKey = {}", selectionKey);
        server.bind(new InetSocketAddress(8080));
        log.info("服务器已启动 ->{}...", server);

        ByteBuffer buffer = ByteBuffer.allocate(16);
        while (true) {
            // 没有事件发生，线程阻塞，有事件发生，恢复运行
            selector.select();
            // 处理事件，selectedKeys包含了所有发生的事件
            Iterator<SelectionKey> selectedKeys = selector.selectedKeys().iterator();
            while (selectedKeys.hasNext()) {
                SelectionKey key = selectedKeys.next();
                log.info("key = {}", key);
                ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                SocketChannel socketChannel = channel.accept();
                log.info("socketChannel = {}", socketChannel);
                int read = socketChannel.read(buffer);
                if (read > 0) {
                    log.info("读取之前...");
                    // 切换至读模式
                    buffer.flip();
                    BufferUtils.print(buffer);
                    // 切换至写模式
                    buffer.clear();
                    log.info("读取之后...");
                }

                selectedKeys.remove();
            }
        }
    }
}
