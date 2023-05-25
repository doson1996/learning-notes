package com.ds.netty.chapter01nio.cs.parallel;

import com.ds.netty.chapter01nio.BufferUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

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
                    log.info("{}建立连接...", channel);
                    worker0.init(channel);
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

        private final Queue<Runnable> queue = new ConcurrentLinkedQueue<>();

        public void init(SocketChannel channel) {
            if (!init) {
                log.info("{} init...", name);
                try {
                    this.selector = Selector.open();
                } catch (IOException e) {
                    log.info("Selector.open ex: ", e);
                    throw new RuntimeException(e);
                }
                thread = new Thread(this, name);
                thread.start();
                init = true;
            }
            // 向队列中添加任务
            queue.add(() -> {
                try {
                    log.info("register...");
                    channel.register(this.selector, SelectionKey.OP_READ);
                } catch (ClosedChannelException e) {
                    throw new RuntimeException(e);
                }
            });
            // 唤醒selector
            selector.wakeup();
            log.info("wakeup...");
        }


        @Override
        public void run() {
            try {
                log.info("{} running...", Thread.currentThread().getName());
                selector.select();  // 阻塞
                log.info("select...");
                // 取出任务并执行
                Runnable task = queue.poll();
                if (task != null)
                    task.run();
                Iterator<SelectionKey> itr = selector.selectedKeys().iterator();
                while (itr.hasNext()) {
                    SelectionKey selectionKey = itr.next();
                    itr.remove();
                    if (selectionKey.isReadable()) {
                        log.info("{} 读事件", Thread.currentThread().getName());
                        ByteBuffer buffer = ByteBuffer.allocate(16);
                        SocketChannel channel = (SocketChannel) selectionKey.channel();
                        channel.read(buffer);
                        buffer.flip();
                        BufferUtils.print(buffer);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        start(8080);
    }

}
