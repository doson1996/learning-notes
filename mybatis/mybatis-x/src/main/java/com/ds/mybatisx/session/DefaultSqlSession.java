package com.ds.mybatisx.session;

import com.ds.mybatisx.config.Configuration;
import com.ds.mybatisx.executor.Executor;
import com.ds.mybatisx.io.Resources;
import com.ds.mybatisx.mapping.MappedStatement;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

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
                InputStream resourceAsStream = Resources.getResourceAsStream("mybatisx.xml");
                SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
                SqlSession sqlSession = sqlSessionFactory.openSession();
                Method invockeMethod;
                Object result;
                if (args == null) {
                    invockeMethod = sqlSession.getClass().getDeclaredMethod(method.getName(), String.class);
                    result = invockeMethod.invoke(sqlSession, statementId);
                } else {
                    invockeMethod = sqlSession.getClass().getDeclaredMethod(method.getName(), String.class, Object.class);
                    result = invockeMethod.invoke(sqlSession, statementId, args[0]);
                }
                sqlSession.close();
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
