package com.ds.zk.service.registry;

import com.ds.zk.server.SimpleServer;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

/**
 * 服务注册 服务端
 *
 * @author ds
 */
public class Server {

    /**
     * 服务启动
     */
    public void start(Integer port) {
        //初始化服务器
        initServer(port);
        //注册
        register("localhost:" + port);
    }

    /**
     * 初始化服务器
     * @param port
     */
    private void initServer(Integer port) {
        new Thread(() -> {
            SimpleServer simpleServer = new SimpleServer();
            simpleServer.start(port);
        }).start();
    }

    /**
     * 注册
     */
    private void register(String hostname) {
        try {
            ZooKeeper zooKeeper = new ZooKeeper("dus.com:2181", 2000, (event)->{});
            zooKeeper.create("/servers", hostname.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            System.out.println("服务已注册...");
        } catch (Exception e) {
            System.out.println("服务注册异常...");
        }
    }


}
