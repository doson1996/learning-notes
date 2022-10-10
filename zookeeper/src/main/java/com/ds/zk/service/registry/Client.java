package com.ds.zk.service.registry;

import cn.hutool.http.HttpUtil;
import org.apache.zookeeper.ZooKeeper;

import java.util.ArrayList;
import java.util.List;

/**
 * 客户端
 * @author ds
 */
public class Client {

    public static final String ROOT_PATH = "/servers";

    private ZooKeeper zkClient;

    private String connectString = "dus.com:2181";

    private int sessionTimeout = 2000;

    public static void main(String[] args) throws Exception {
        Client client = new Client();
        // 创建zk连接
        client.getConnect();

        //监听 /servers 下面的节点
        client.getServerList();

        client.deal();
    }

    private void deal() throws InterruptedException {
        Thread.sleep(Integer.MAX_VALUE);
    }

    private void getServerList() throws Exception {
        List<String> servers = new ArrayList<>();
        List<String> nodes = zkClient.getChildren(ROOT_PATH, true);
        for (String node : nodes) {
            byte[] data = zkClient.getData(ROOT_PATH + "/" + node, false, null);
            servers.add(new String(data));
        }

        for (String server : servers) {
            System.out.println("server = " + server);
            // 请求服务器
            String result = HttpUtil.get(server + "/test?param=" + server);
            System.out.println("result = " + result);
        }

    }

    private void getConnect() throws Exception {
        zkClient = new ZooKeeper(connectString, sessionTimeout, (event) -> {
            try {
                getServerList();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

}
