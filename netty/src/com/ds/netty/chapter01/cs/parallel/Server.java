package com.ds.netty.chapter01.cs.parallel;

import com.ds.netty.chapter01.BufferUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * @author ds
 * @date 2023/5/23
 * @description
 */
@Slf4j
public class Server {

    private static ServerSocketChannel server;

    private static volatile boolean isRun = false;

    @SneakyThrows
    public static void start(int port) {
        Selector acceptSelector = Selector.open();
        server = ServerSocketChannel.open();
        server.configureBlocking(false);
        server.bind(new InetSocketAddress(port));
        server.register(acceptSelector, SelectionKey.OP_ACCEPT);

        Worker worker0 = new Worker("worker-0");
        isRun = true;
        log.info("服务器-{}-已启动...", server);
        while (isRun) {
            acceptSelector.select();
            Iterator<SelectionKey> itr = acceptSelector.selectedKeys().iterator();
            while (itr.hasNext()) {
                SelectionKey sk = itr.next();
                itr.remove();
                if (sk.isAcceptable()) {
                    SocketChannel channel = server.accept();
                    channel.configureBlocking(false);
                    worker0.init();
                    channel.register(worker0.selector, SelectionKey.OP_READ);
                }
            }
        }
    }

    private static class Worker implements Runnable {

        private String name;

        private Selector selector;

        private Thread thread;

        private volatile boolean init = false;

        public Worker(String name) {
            this.name = name;
        }

        @SneakyThrows
        public void init() {
            if (!init) {
                selector = Selector.open();
                thread = new Thread(this, name);
                thread.start();
                init = true;
            }
        }

        @SneakyThrows
        @Override
        public void run() {
            selector.select();
            Iterator<SelectionKey> itr = selector.selectedKeys().iterator();
            while (itr.hasNext()) {
                SelectionKey selectionKey = itr.next();
                itr.remove();
                if (selectionKey.isReadable()) {
                    ByteBuffer buffer = ByteBuffer.allocate(16);
                    SocketChannel channel = (SocketChannel) selectionKey.channel();
                    channel.configureBlocking(false);
                    channel.read(buffer);
                    buffer.flip();
                    BufferUtils.print(buffer);
                }
            }
        }
    }

    public static void main(String[] args) {
        start(8080);
    }

}
