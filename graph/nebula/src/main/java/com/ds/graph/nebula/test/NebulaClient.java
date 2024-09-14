package com.ds.graph.nebula.test;

import com.vesoft.nebula.client.graph.NebulaPoolConfig;
import com.vesoft.nebula.client.graph.data.HostAddress;
import com.vesoft.nebula.client.graph.data.ResultSet;
import com.vesoft.nebula.client.graph.net.NebulaPool;
import com.vesoft.nebula.client.graph.net.Session;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author ds
 * @date 2024/9/13
 * @description
 */
public class NebulaClient {

    private static Session getSession() throws Exception {
        NebulaPoolConfig nebulaPoolConfig = new NebulaPoolConfig();
        nebulaPoolConfig.setMaxConnSize(10);
        List<HostAddress> addresses = Arrays.asList(new HostAddress("192.168.233.128", 9669),
                new HostAddress("192.168.233.128", 9670));
        NebulaPool pool = new NebulaPool();
        pool.init(addresses, nebulaPoolConfig);
        return pool.getSession("root", "1", false);
    }

    public static ResultSet exec(String stmt) {
        Session session = null;
        try {
            session = getSession();
            ResultSet execute = session.execute(stmt);
            return execute;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            assert session != null;
            session.release();
        }

        return null;
    }

    public static ResultSet exec(String stmt, Map<String, Object> params) {
        Session session = null;
        try {
            session = getSession();
            return session.execute(stmt);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            assert session != null;
            session.release();
        }

        return null;
    }

}
