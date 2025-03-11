package com.ds.mybatisx.session;

import java.io.Closeable;
import java.util.List;

/**
 * @author ds
 * @date 2023/8/9
 * @description
 */
public interface SqlSession extends Closeable {

    /**
     * 查询单个结果
     *
     * @param statementId
     * @param <T>
     * @return
     */
    <T> T selectOne(String statementId);

    /**
     * 查询单个结果
     *
     * @param statementId
     * @param parameter
     * @param <T>
     * @return
     */
    <T> T selectOne(String statementId, Object parameter);

    /**
     * 查询多个结果
     *
     * @param statementId
     * @param <E>
     * @return
     */
    <E> List<E> selectList(String statementId);

    /**
     * 查询多个结果
     *
     * @param statementId
     * @param parameter
     * @param <E>
     * @return
     */
    <E> List<E> selectList(String statementId, Object parameter);

    /**
     * 获取mapper
     *
     * @param type
     * @param <T>
     * @return
     */
    <T> T getMapper(Class<T> type);

}
