package com.ds.netty.chapter02netty.d9rpc.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ds
 * @date 2023/7/19
 * @description
 */
public class ServiceFactory {

    public static Map<Class<?>, Object> map = new ConcurrentHashMap<>();

    static {
        map.put(HelloService.class, new HelloServiceImpl());
    }

    // 根据 接口类 获取 实现类
    public static <T> T getService(Class<T> interfaceClass) {
        return (T) map.get(interfaceClass);
    }

}
