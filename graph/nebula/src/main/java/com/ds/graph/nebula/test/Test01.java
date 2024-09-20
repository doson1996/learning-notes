package com.ds.graph.nebula.test;

import com.vesoft.nebula.Edge;
import com.vesoft.nebula.Row;
import com.vesoft.nebula.Value;
import com.vesoft.nebula.Vertex;
import com.vesoft.nebula.client.graph.NebulaPoolConfig;
import com.vesoft.nebula.client.graph.data.HostAddress;
import com.vesoft.nebula.client.graph.data.ResultSet;
import com.vesoft.nebula.client.graph.data.ValueWrapper;
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
        String nql = "GO FROM \"player102\" OVER * BIDIRECT YIELD edge AS e;";
        nql = "GO 2 STEPS FROM \"player102\" OVER follow YIELD dst(edge);";
        nql = "GO FROM \"player100\" OVER follow REVERSELY YIELD src(edge) as player;";
        nql = " MATCH (v1:player{name: \"LeBron James\"}) <-[r:follow]-> (v2)\n" +
                "      RETURN type(r) AS Type, v1,v2, r";

//        String s = session.executeJson("USE basketballplayer; MATCH (v:player) RETURN v;");
        String result = session.executeJson("USE basketballplayer; " + nql);
        System.out.println("result = " + result);

        ResultSet r1 = session.execute("USE basketballplayer; " + nql);

        List<Row> rows = r1.getRows();
        int i = 0;
        while (i < rows.size()) {
            for (ValueWrapper value : r1.rowValues(i)) {
                if (value.isEdge()) {
                    System.out.println("E: " + value.asRelationship());
                }

                if (value.isVertex()) {
                    System.out.println("V: " + value.asNode());
                }
            }
            i++;

        }

        session.release();
        pool.close();
    }
}
