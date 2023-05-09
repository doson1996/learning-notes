package com.ds.netty.chapter01.cs;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
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
                channel.configureBlocking(false);
                SocketChannel socketChannel = channel.accept();
                log.info("socketChannel = {}", socketChannel);
                int read = socketChannel.read(buffer);
                if (read > 0) {
                    // 切换至读模式
                    buffer.flip();
                    String params = new String(buffer.array()).trim();
                    log.info("接收到参数: {}", params);
                    response(params,socketChannel);
                    //  BufferUtils.print(buffer);
                    // 切换至写模式
                    buffer.clear();
                }
                selectedKeys.remove();
            }
        }
    }

    /**
     * 响应客户端
     * @param params
     * @param socketChannel
     */
    private static void response(String params, SocketChannel socketChannel) {
        Socket socket = socketChannel.socket();
        try {
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(("服务器返回：" + params).getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
