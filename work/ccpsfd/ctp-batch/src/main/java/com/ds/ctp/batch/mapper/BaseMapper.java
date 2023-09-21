package com.ds.ctp.batch.mapper;

import com.alibaba.fastjson2.JSONObject;

/**
 * @author ds
 * @date 2023/9/21
 * @description
 */
public interface BaseMapper<T> {

    int insert(T data);

    int delete(T condition);

    int update(T data, T condition);

}
