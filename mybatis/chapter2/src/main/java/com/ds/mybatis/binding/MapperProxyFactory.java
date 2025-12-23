package com.ds.mybatis.binding;

import java.lang.reflect.Proxy;
import java.util.Map;

/**
 * @author ds
 * @date 2025/6/18
 * @description
 */
public class MapperProxyFactory<T> {

    private Class<T> mapperInterface;

    public MapperProxyFactory(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    public T newInstance(Map<String, String> sqlSession) {
        return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(), new Class[]{mapperInterface}, new MapperProxy(mapperInterface, sqlSession));
    }

}
