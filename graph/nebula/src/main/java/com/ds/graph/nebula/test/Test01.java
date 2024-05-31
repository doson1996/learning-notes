package com.ds.graph.nebula.test;

import com.vesoft.nebula.client.graph.NebulaPoolConfig;
import com.vesoft.nebula.client.graph.data.HostAddress;
import com.vesoft.nebula.client.graph.data.ResultSet;
import com.vesoft.nebula.client.graph.exception.IOErrorException;
import com.vesoft.nebula.client.graph.net.NebulaPool;
import com.vesoft.nebula.client.graph.net.Session;


import java.util.Arrays;
import java.util.List;

/**
 * @author ds
 * @date 2024/5/23
 * @description
 */
public class Test01 {
    public static void main(String[] args) throws Exception {
        NebulaPoolConfig nebulaPoolConfig = new NebulaPoolConfig();
        nebulaPoolConfig.setMaxConnSize(10);
        List<HostAddress> addresses = Arrays.asList(new HostAddress("192.168.233.128", 9669),
                new HostAddress("192.168.233.128", 9670));
        NebulaPool pool = new NebulaPool();
        pool.init(addresses, nebulaPoolConfig);
        Session session = pool.getSession("root", "1", false);
        ResultSet execute = session.execute("SHOW HOSTS;");
        System.out.println("execute = " + execute);
        session.release();
        pool.close();
    }
}
