package com.ds.netty.chapter01nio.cs;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author ds
 * @date 2023/5/22
 * @description
 */
@Slf4j
public class ServerV3 {

    private static final AtomicBoolean RUN = new AtomicBoolean(false);

    private static ServerSocketChannel SERVER;

    public static void main(String[] args) {
        new Thread(()->{
            start(8080);
        }).start();

        new Thread(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            close();
        }).start();
    }

    public static void start(int port) {
        try {
            Selector selector = Selector.open();

            SERVER = ServerSocketChannel.open();
            SERVER.configureBlocking(false);
            SERVER.bind(new InetSocketAddress(port));
            SERVER.register(selector, SelectionKey.OP_ACCEPT);
            RUN.set(true);
            log.info("服务器已启动 ->{}...", SERVER);

            while (RUN.get()) {
                selector.select();
                Iterator<SelectionKey> itr = selector.selectedKeys().iterator();
                while (itr.hasNext()) {
                    SelectionKey key = itr.next();
                    itr.remove();
                    if (key.isAcceptable()) {
                        SocketChannel sc = SERVER.accept();
                        sc.configureBlocking(false);
                        SelectionKey selectionKey = sc.register(selector, 0, null);

                        // 向客户端发送大量数据
                        StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < 1000000; i++) {
                            sb.append("a");
                        }

                        ByteBuffer buffer = Charset.defaultCharset().encode(sb.toString());
                        // 非阻塞方式
                        int write = sc.write(buffer);
                        System.out.println("首次写入: " + write);
                        if (buffer.hasRemaining()) {
                            // 原有监听事件 + 监听可写事件
                            selectionKey.interestOps(selectionKey.interestOps() + SelectionKey.OP_WRITE);
                            selectionKey.attach(buffer);
                        }
                        // 这种方式会一直阻塞，直到全部内容发送成功
//                        while (buffer.hasRemaining()) {
//                            // 实际写入字节
//                            int write = sc.write(buffer);
//                            System.out.println("write = " + write);
//                        }
                    } else if (key.isWritable()) {
                        ByteBuffer buffer = (ByteBuffer) key.attachment();
                        SocketChannel sc = (SocketChannel) key.channel();
                        sc.configureBlocking(false);
                        int write = sc.write(buffer);
                        System.out.println("后续写入: " + write);
                        // 清理操作
                        if (!buffer.hasRemaining()) {
                            key.attach(null);
                            // 原有监听事件 - 监听可写事件
                            key.interestOps(key.interestOps() - SelectionKey.OP_WRITE);
                        }
                    }
                }
            }
        } catch (Exception e) {
            log.error("server starting error: ", e);
        }
    }

    public static void close() {
        try {
            RUN.set(false);
            if (SERVER != null)
                SERVER.close();
            log.info("server close...");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
