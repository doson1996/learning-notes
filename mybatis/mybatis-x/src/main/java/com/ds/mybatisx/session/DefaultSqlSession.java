package com.ds.mybatisx.session;

import com.ds.mybatisx.config.Configuration;
import com.ds.mybatisx.executor.Executor;

import java.io.IOException;
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
        return null;
    }

    @Override
    public <E> List<E> selectList(String statementId, Object parameter) {
        List<E> list = executor.doQuery(statementId, parameter);
        return list;
    }

    @Override
    public void close() throws IOException {
        executor.close();
    }
}
