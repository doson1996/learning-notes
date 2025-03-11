package com.ds.mybatisx.executor;

import java.util.List;

/**
 * @author ds
 * @date 2023/8/9
 * @description
 */
public interface Executor {
    /**
     * 执行查询
     *
     * @param statementId
     * @param parameter
     * @param <E>
     * @return
     */
    <E> List<E> doQuery(String statementId, Object parameter);

    /**
     * 关闭资源
     */
    void close();
}
