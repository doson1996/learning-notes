package com.ds.zk.service.registry;

/**
 * 测试服务器动态上下线
 *
 * @author ds
 */
public class Test {

    public static void main(String[] args) {
        String[] ports = new String[args.length];
        if (args.length == 0) {
            ports = new String[]{"8080"};
        } else {
            ports = args[0].split(",");
        }
        for (String port : ports) {
            Server server = new Server();
            server.start(Integer.valueOf(port));
        }
    }

}
