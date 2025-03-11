package com.ds.mybatisx.session;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.List;

import com.ds.mybatisx.config.Configuration;
import com.ds.mybatisx.executor.Executor;
import com.ds.mybatisx.mapping.MappedStatement;

/**
 * @author ds
 * @date 2023/8/9
 * @description
 */
public class DefaultSqlSession implements SqlSession {

    private final Configuration configuration;
    private final Executor executor;

    public DefaultSqlSession(Configuration configuration, Executor executor) {
        this.configuration = configuration;
        this.executor = executor;
    }

    @Override
    public <T> T selectOne(String statementId) {
        List<T> list = selectList(statementId);
        if (list.size() == 1)
            return list.get(0);
        if (list.size() > 1)
            throw new RuntimeException("预期是一个返回结果，但是发现:" + list.size());
        return null;
    }

    @Override
    public <T> T selectOne(String statementId, Object parameter) {
        List<T> list = selectList(statementId, parameter);
        if (list.size() == 1)
            return list.get(0);
        if (list.size() > 1)
            throw new RuntimeException("预期是一个返回结果，但是发现:" + list.size());
        return null;
    }

    @Override
    public <E> List<E> selectList(String statementId) {
        return selectList(statementId, null);
    }

    @Override
    public <E> List<E> selectList(String statementId, Object parameter) {
        List<E> list = executor.doQuery(statementId, parameter);
        return list;
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        if (!type.isInterface())
            throw new RuntimeException("mapper请定义为接口");
        Object mapper = Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{type}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                String statementId = method.getDeclaringClass().getName() + "." + method.getName();
                MappedStatement statement = configuration.get(statementId);
                String sqlCommandType = statement.getSqlCommandType();
                Type genericReturnType = method.getGenericReturnType();
                // 参数
                Object param = args != null ? args[0] : null;
                Object result = null;
                switch (sqlCommandType) {
                    case "select":
                        if (genericReturnType instanceof ParameterizedType) {
                            result = selectList(statementId, param);
                        } else {
                            result = selectOne(statementId, param);
                        }
                    case "update":
                        break;
                    case "insert":
                        break;
                    case "delete":
                        break;

                }

                return result;
            }
        });
        return (T) mapper;
    }

    @Override
    public void close() throws IOException {
        executor.close();
    }
}
