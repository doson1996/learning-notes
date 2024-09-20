package com.ds.concurrent.chapter02.connectionpool;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * @Author ds
 * @Date 2021/4/6 11:15
 * @Description
 */
public class ConnectionPool {

    private final int MAX_SIZE = 100;

    private final LinkedList<Connection> pool = new LinkedList<>();

    public ConnectionPool(int initialSize) {
        if (initialSize < 0 || initialSize > MAX_SIZE) {
            throw new IllegalArgumentException("illegal parameter initialSize：" + initialSize);
        }

        for (int i = 0; i < initialSize; i++) {
            pool.addLast(ConnectionDriver.createConnection());
        }
    }

    public void releaseConnection(Connection connection) {
        if (connection != null) {
            synchronized (pool) {
                // 连接释放后需要进行通知，这样其他消费者能够感知到连接池中已经归还了一个连接
                pool.addLast(connection);
                pool.notifyAll();
            }
        }
    }

    /**
     * 在 mills 内无法获取到连接，将会返回 null
     *
     * @param mills
     * @return
     */
    public Connection fetchConnection(long mills) throws InterruptedException {
        synchronized (pool) {
            if (mills < 0) {
                while (pool.isEmpty()) {
                    pool.wait();
                }
                return pool.removeFirst();
            } else {
                long future = System.currentTimeMillis() + mills;
                long remaining = mills;
                if (pool.isEmpty() && remaining > 0) {
                    pool.wait(remaining);
                    remaining = future - System.currentTimeMillis();
                }

                Connection connection = null;
                if (!pool.isEmpty()) {
                    connection = pool.removeFirst();
                }
                return connection;
            }
        }

    }
}
