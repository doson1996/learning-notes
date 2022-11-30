package com.ds.zk;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * @author ds
 */
public class ZkClient {

    private static final String HOST = "dy.com:2181";

    private ZooKeeper zooKeeper;

    @Before
    public void init() throws Exception {
        zooKeeper = new ZooKeeper(HOST, 2000, (event) -> {
            // 注册一次，监听一次
            System.out.println("------------------");
            try {
                List<String> children = zooKeeper.getChildren("/", true);
                for (String child : children) {
                    System.out.println("child = " + child);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 创建持久节点
     *
     * @throws Exception
     */
    @Test
    public void create() throws Exception {
        String result = zooKeeper.create("/zk2", "hello zk".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println("result = " + result);
    }

    /**
     * 监听
     *
     * @throws Exception
     */
    @Test
    public void getChildren() throws Exception {
        List<String> result = zooKeeper.getChildren("/", true);

        for (String path : result) {
            System.out.println("path = " + path);
        }

        Thread.sleep(Integer.MAX_VALUE);
    }

    /**
     * 节点是否存在
     *
     * @throws Exception
     */
    @Test
    public void exist() throws Exception {
        Stat stat = zooKeeper.exists("/zk", false);
        System.out.println("stat = " + stat);
        System.out.println(stat == null ? "节点不存在" : "节点已存在");
    }

}
