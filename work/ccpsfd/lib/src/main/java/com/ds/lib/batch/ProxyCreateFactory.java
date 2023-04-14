package com.ds.lib.batch;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author ds
 * @date 2023/4/14
 * @description
 */
public class ProxyCreateFactory {
    public static <T> T getProxyObject(Class<T> clazz) {
        // 该接口的Class对象是被那个类加载器加载的
        ClassLoader classLoader = clazz.getClassLoader();
        // 获取到该接口所有的interface
        Class<?>[] interfaces = clazz.getInterfaces();

        // jdk代理必须的handler，代理对象的方法执行就会调用这里的invoke方法。自动传入调用的方法 + 方法参数
        InvocationHandler invocationHandler = (proxy, method, args) -> method.invoke(clazz, args);

        T proxy = (T) Proxy.newProxyInstance(classLoader, interfaces, invocationHandler);
        // 返回代理对象
        return proxy;
    }
}
