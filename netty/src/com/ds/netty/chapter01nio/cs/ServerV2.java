package com.ds.netty.chapter01nio.cs;

import com.ds.netty.chapter01nio.BufferUtils;
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

        while (true) {
            // 没有事件发生，线程阻塞，有事件发生，恢复运行
            // 有事件未处理，不会阻塞 （事件发生后，要么处理，要么取消）
            selector.select();
            // 处理事件，selectedKeys包含了所有发生的事件
            Iterator<SelectionKey> selectedKeys = selector.selectedKeys().iterator();
            while (selectedKeys.hasNext()) {
                SelectionKey key = selectedKeys.next();
                selectedKeys.remove();

                log.info("key = {}", key);
                if (key.isAcceptable()) {
                    ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                    SocketChannel socketChannel = channel.accept();
                    socketChannel.configureBlocking(false);
                    log.info("socketChannel = {}", socketChannel);
                    // 为SelectionKey设置附件
                    ByteBuffer buffer = ByteBuffer.allocate(16);
                    socketChannel.register(selector, SelectionKey.OP_READ, buffer);
                } else if (key.isReadable()) {
                    try {
                        SocketChannel channel = (SocketChannel) key.channel();
                        channel.configureBlocking(false);
                        // 获取SelectionKey中的附件
                        ByteBuffer buffer = (ByteBuffer) key.attachment();
                        int read = channel.read(buffer);
                        if (read == -1) {  //客户端正常断开，返回-1
                            key.cancel();
                        }
                        if (read > 0) {
                            spit(buffer);
                            if (buffer.position() == buffer.limit()) { // 说明没有拆分出一条完整的消息，进行扩容后放入selectionKey
                                ByteBuffer newBuffer = ByteBuffer.allocate(buffer.capacity() << 1);
                                buffer.flip();
                                newBuffer.put(buffer);
                                key.attach(newBuffer);
                            }

                            // 切换至读模式
                            //buffer.flip();
                            // 请求处理
                            //String params = request(buffer);
                            // 响应
                            // response(params, channel);
                            //  BufferUtils.print(buffer);
                            // 切换至写模式
                            //buffer.clear();
                        }
                    } catch (IOException e) {
                        log.error("发生异常：", e);
                        key.cancel();
                    }
                }
            }
        }
    }

    /**
     * 请求处理
     *
     * @param buffer
     * @return
     */
    private static String request(ByteBuffer buffer) {
        String params = new String(buffer.array()).trim();
        log.info("接收到参数: {}", params);
        return params;
    }

    /**
     * 响应客户端
     *
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

    /**
     * buffer拆分出完整的消息
     * @param source
     */
    public static void spit(ByteBuffer source) {
        source.flip();
        for (int i = 0; i < source.limit(); i++) {
            // get(i) 不会更新position
            if (source.get(i) == '\n') {
                // source开始读的位置
                int len = i + 1 - source.position();
                ByteBuffer target = ByteBuffer.allocate(len);
                for (int j = 0; j < len; j++) {
                    target.put(source.get());
                }
                target.flip();
                BufferUtils.print(target);
            }
        }
        source.compact();
    }

}
