package com.ds.mybatisx.session;

import com.ds.mybatisx.config.Configuration;
import com.ds.mybatisx.executor.Executor;
import com.ds.mybatisx.executor.SimpleExecutor;

/**
 * @author ds
 * @date 2023/8/9
 * @description
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        // 1.创建执行器
        Executor executor = new SimpleExecutor();
        // 2.创建SqlSession
        return new DefaultSqlSession(configuration, executor);
    }
}
