package com.ds.graph.nebula.data;


import com.vesoft.nebula.client.graph.NebulaPoolConfig;
import com.vesoft.nebula.client.graph.data.HostAddress;
import com.vesoft.nebula.client.graph.data.ResultSet;
import com.vesoft.nebula.client.graph.exception.IOErrorException;
import com.vesoft.nebula.client.graph.net.NebulaPool;
import com.vesoft.nebula.client.graph.net.Session;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.PreDestroy;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ds
 * @date 2024/11/4
 * @description
 */
@Slf4j
public class NebulaRepo {

    public static NebulaRepo getNebulaRepo() {
        return new NebulaRepo();
    }

    /**
     * Nebula连接池，使用StoreURL作为键来存储对应的NebulaPool实例。
     */
    private static final Map<String, NebulaPool> NEBULA_POOL = new ConcurrentHashMap<>();


    /**
     * 用于 NebulaPool 实例创建的锁对象，保证线程安全。
     */
    private static final Object LOCK = new Object();

    /**
     * 连接池中最大连接数。
     */
    private Integer maxConnSize = 10;


    /**
     * 执行语句
     *
     * @param nql
     * @return
     */
    public ResultSet executeUseDatabase(String nql) {
        try {
            StringBuffer useNql = getUseDatabase();
            useNql.append(nql);
            System.out.println("nql 语句打印:" + useNql);
            Session session = getSession();
            ResultSet result = session.execute(useNql.toString());
            session.release();
            checkResponse(result);
            return result;
        } catch (IOErrorException e) {
//            throw new UnexpectedStatusException(ServerGdbStatus.GDB_OPERATION_ERROR, graph, e.getMessage());
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 执行语句
     *
     * @param nql
     * @return
     */
    public ResultSet execute(String nql) {
        try {
            System.out.println("nql 语句打印: " + nql);
            Session session = getSession();
            ResultSet result = session.execute(nql);
            session.release();
            checkResponse(result);
            return result;
        } catch (IOErrorException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 校验图库返回结果是否存在异常情况
     *
     * @param resultSet
     * @return
     */
    private void checkResponse(ResultSet resultSet) {
        if (ObjectUtils.isNotEmpty(resultSet.getErrorMessage())) {
            throw new RuntimeException(resultSet.getErrorMessage());
        }
    }

    /**
     * 获取到Nebula数据库的会话。
     *
     * @return Session对象，用于执行图形数据库操作
     * @throws RuntimeException 如果获取会话过程中发生错误
     */
    private Session getSession() {
        try {
            NebulaPool nebulaPool = NEBULA_POOL.get(Config.URL);
            if (nebulaPool == null) {
                synchronized (LOCK) {
                    nebulaPool = NEBULA_POOL.get(Config.URL);
                    if (nebulaPool == null) {
                        nebulaPool = createNebulaPool(Config.URL);
                        NEBULA_POOL.put(Config.URL, nebulaPool);
                    }
                }
            }
            return nebulaPool.getSession(Config.USER, Config.PASSWORD, true);
        } catch (Exception e) {
            log.error("Failed to get nebula session: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to get nebula session");
        }
    }

    /**
     * 创建Nebula连接池。
     *
     * @param url 数据库连接URL
     * @return 初始化后的NebulaPool实例
     * @throws Exception 如果URL格式错误或创建过程中发生其他错误
     */
    private NebulaPool createNebulaPool(String url) throws Exception {
        if (StringUtils.isEmpty(url)) {
            throw new UnknownHostException("url is empty");
        }

        url = url.trim();

        if (!url.contains(":")) {
            throw new UnknownHostException("url format error");
        }

        List<HostAddress> addresses = new ArrayList<>();
        for (String hostAddress : url.split(",")) {
            if (hostAddress.contains(":")) {
                String[] parts = hostAddress.split(":");
                if (parts.length != 2 || parts[0].isEmpty() || parts[1].isEmpty()) {
                    throw new UnknownHostException("Invalid IP or port in URL: " + hostAddress);
                }
                int port = Integer.parseInt(parts[1]);
                addresses.add(new HostAddress(parts[0], port));
            }
        }

        NebulaPoolConfig nebulaPoolConfig = new NebulaPoolConfig();
        nebulaPoolConfig.setMaxConnSize(maxConnSize);
        NebulaPool pool = new NebulaPool();
        pool.init(addresses, nebulaPoolConfig);
        return pool;
    }


    /**
     * 构建图查询语句前缀
     *
     * @return
     */
    private StringBuffer getUseDatabase() {
        StringBuffer nql = new StringBuffer();
        nql.append("USE ").append(Config.GRAPH).append("; ");
        return nql;
    }

    /**
     * 关闭所有Nebula连接池，并清空池列表。
     */
    @PreDestroy
    public void close() {
        for (NebulaPool nebulaPool : NEBULA_POOL.values()) {
            nebulaPool.close();
        }
        NEBULA_POOL.clear();
    }

}
