package com.ds.zk.service.registry;

/**
 * @author ds
 */
public class Test {

    public static void main(String[] args) {
        Server server1 = new Server();
        server1.start(8080);

        Server server2 = new Server();
        server2.start(8081);
    }

}
