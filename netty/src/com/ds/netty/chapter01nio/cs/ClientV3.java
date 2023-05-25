package com.ds.netty.chapter01nio.cs;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author ds
 * @date 2023/5/5
 * @description
 */
public class ClientV3 {
    public static void main(String[] args) throws Exception {
        SocketChannel client = SocketChannel.open();
        client.connect(new InetSocketAddress("localhost", 8080));
        ByteBuffer buffer = ByteBuffer.allocate(1024 * 1024);
        int count = 0;
        while (count < 1000000) {
            count += client.read(buffer);
            System.out.println("count = " + count);
            buffer.clear();
        }
    }
}
