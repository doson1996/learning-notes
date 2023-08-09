package com.ds.mybatisx.session;

import com.ds.mybatisx.config.Configuration;
import com.ds.mybatisx.executor.Executor;

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
}
