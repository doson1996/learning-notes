package com.ds.concurrent.chapter02.connectionpool;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;

import com.ds.concurrent.util.ThreadUtils;

/**
 * @Author ds
 * @Date 2021/4/6 11:17
 * @Description
 */
public class ConnectionDriver {

    static class ConnectionHandler implements InvocationHandler {

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if ("commit".equals(method.getName())) {
                ThreadUtils.milliseconds(100);
            }
            return null;
        }
    }

    /**
     * 创建一个 Connection 的代理，在 commit 时休眠 100 毫秒
     *
     * @return
     */
    public static Connection createConnection() {
        ClassLoader classLoader = ConnectionDriver.class.getClassLoader();
        Class[] clazzs = {Connection.class};
        ConnectionHandler connectionHandler = new ConnectionHandler();
        return (Connection) Proxy.newProxyInstance(classLoader, clazzs, connectionHandler);
    }
}
