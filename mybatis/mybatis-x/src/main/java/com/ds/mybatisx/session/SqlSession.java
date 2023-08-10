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
     * @param statementId
     * @return
     * @param <T>
     */
    <T> T selectOne(String statementId);

    /**
     * 查询单个结果
     * @param statementId
     * @param parameter
     * @return
     * @param <T>
     */
    <T> T selectOne(String statementId, Object parameter);

    /**
     * 查询多个结果
     * @param statementId
     * @return
     * @param <E>
     */
    <E> List<E> selectList(String statementId);

    /**
     * 查询多个结果
     * @param statementId
     * @param parameter
     * @return
     * @param <E>
     */
    <E> List<E> selectList(String statementId, Object parameter);

}
