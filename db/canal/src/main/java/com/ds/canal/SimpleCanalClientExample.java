package com.ds.canal;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;

import java.net.InetSocketAddress;

/**
 * @author ds
 */
public class SimpleCanalClientExample {

    public static void main(String[] args) {
        String destination = "example";
        CanalConnector connector = CanalConnectors.newSingleConnector(new InetSocketAddress("127.0.0.1", 11111),
                destination,
                "canal",
                "canal");



    }

}
