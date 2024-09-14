package com.ds.graph.nebula.test;

import com.vesoft.nebula.Row;
import com.vesoft.nebula.Value;
import com.vesoft.nebula.client.graph.SessionPool;
import com.vesoft.nebula.client.graph.SessionPoolConfig;
import com.vesoft.nebula.client.graph.data.HostAddress;
import com.vesoft.nebula.client.graph.data.ResultSet;
import com.vesoft.nebula.client.graph.exception.AuthFailedException;
import com.vesoft.nebula.client.graph.exception.BindSpaceFailedException;
import com.vesoft.nebula.client.graph.exception.ClientServerIncompatibleException;
import com.vesoft.nebula.client.graph.exception.IOErrorException;

import java.util.Arrays;
import java.util.List;

/**
 * @author ds
 * @date 2024/9/13
 * @description
 */
public class Test02 {
    public static void main(String[] args) {
        List<HostAddress> addresses = Arrays.asList(new HostAddress("192.168.233.128", 9669));
        String spaceName = "basketballplayer";
        String user = "root";
        String password = "1";
        SessionPoolConfig sessionPoolConfig = new SessionPoolConfig(addresses, spaceName, user,
                password);
        SessionPool sessionPool = new SessionPool(sessionPoolConfig);

        if (!sessionPool.init()) {
            return;
        }
        ResultSet resultSet = null;
        try {
            resultSet = sessionPool.execute("match (v:player) return v limit 1;");
            System.out.println(resultSet.toString());
        } catch (IOErrorException | ClientServerIncompatibleException | AuthFailedException
                 | BindSpaceFailedException e) {
            e.printStackTrace();
            sessionPool.close();
            System.exit(1);
        }

        List<String> columnNames = resultSet.getColumnNames();
        System.out.println("columnNames = " + columnNames);

        List<Row> rows = resultSet.getRows();
        for (Row row : rows) {
            List<Value> values = row.getValues();
            for (Value value : values) {
                System.out.println("value = " + value);
            }
        }

        sessionPool.close();
    }
}
