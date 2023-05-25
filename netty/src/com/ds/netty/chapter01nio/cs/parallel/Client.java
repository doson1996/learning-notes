package com.ds.netty.chapter01nio.cs.parallel;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author ds
 * @date 2023/5/5
 * @description
 */
public class Client {
    public static void main(String[] args) throws Exception {
        SocketChannel client = SocketChannel.open();
        client.connect(new InetSocketAddress("localhost", 8080));
        client.write(ByteBuffer.wrap("012345...".getBytes()));
    }
}
