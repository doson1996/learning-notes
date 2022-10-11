package com.ds.zk.lock;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * zk实现分布式锁
 *
 * @author ds
 */
public class ZkLock {

    private ZooKeeper zk;

    private int sessionTimeout = 2000;

    private String connectString = "dus.com";

    /**
     * 加锁资源
     */
    private String resource;

    /**
     * 前一个节点路径
     */
    private String preNodePath;

    /**
     * 当前节点
     */
    private String currentNode;

    private static final String ROOT_PATH = "/locks";

    private CountDownLatch waitLatch = new CountDownLatch(1);

    public ZkLock() throws Exception {
        // 创建zk连接
        zk = new ZooKeeper(connectString, sessionTimeout, (event) -> {
            if (event.getType().equals(Watcher.Event.EventType.NodeDeleted) && event.getPath().equals(preNodePath)) {
                waitLatch.countDown();
            }
        });

        //判断根节点是否存在
        Stat root = zk.exists(ROOT_PATH, false);
        if (root == null) {
            zk.create(ROOT_PATH, ROOT_PATH.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
    }

    /**
     * 加锁
     *
     * @param resource
     * @return
     */
    public boolean lock(String resource) {
        try {
            this.resource = resource;
            //创建当前节点
            currentNode = zk.create(ROOT_PATH + "/" + resource + "-", null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            // 判断当前节点是否最小节点
            List<String> children = zk.getChildren(ROOT_PATH, false);
            // 如果只有一个节点，加锁成功
            if (children.size() == 1) {
                return true;
            }
            // 对节点进行排序
            Collections.sort(children);
            // 如果有多个节点
            String thisNode = currentNode.substring((ROOT_PATH + "/").length());
            int index = children.indexOf(thisNode);
            //数据异常
            if (index == -1) {
                return false;
            }
            // 当前节点为最小节点，加锁成功
            if (index == 0) {
                return true;
            }
            // 非最小节点，监听前一个节点
            preNodePath = ROOT_PATH + "/" + children.get(index - 1);
            zk.getData(preNodePath, true, null);
            waitLatch.await();
        } catch (Exception e) {
            System.out.println("加锁异常: " + e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * 解锁
     */
    public void unlock() {
        try {
            zk.delete(currentNode, -1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
