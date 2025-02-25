package com.ds.dp.adapter;

import java.util.List;

/**
 * @Author ds
 * @Date 2021/3/5 16:06
 * @Version 1.0
 * @Description 日志操作接口 数据库
 */
public interface LogDbOperateApi {

    /**
     * 插入日志记录
     *
     * @param log 日志记录
     */
    void insert(LogModel log);

    /**
     * 获取日志记录
     *
     * @return 日志记录列表
     */
    List<LogModel> getLogs();

}
