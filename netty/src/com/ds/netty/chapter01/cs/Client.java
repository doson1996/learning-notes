package com.ds.netty.chapter01.cs;


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
        client.write(ByteBuffer.wrap("client a...".getBytes()));
        System.out.println("...");
//        while (true) {
//            client.write(ByteBuffer.wrap("client a...".getBytes()));
//            Thread.sleep(1000);
//          //  client.close();
//        }
    }
}
