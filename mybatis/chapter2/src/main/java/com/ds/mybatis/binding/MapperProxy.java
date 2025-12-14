package com.ds.mybatis.binding;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author ds
 * @date 2025/6/18
 * @description
 */
public class MapperProxy<T> implements InvocationHandler {

    private final Class<T> mapperInterface;

    private final Map<String, String> sqlSession;

    public MapperProxy(Class<T> mapperInterface, Map<String, String> sqlSession) {
        this.mapperInterface = mapperInterface;
        this.sqlSession = sqlSession;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String sql = sqlSession.get(mapperInterface.getName() + "." + method.getName());
        System.out.println("模拟执行sql： " + sql);
        return "张三";
    }

}
