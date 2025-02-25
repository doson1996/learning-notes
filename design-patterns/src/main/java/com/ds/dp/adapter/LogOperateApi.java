package com.ds.dp.adapter;

import java.util.List;

/**
 * @Author ds
 * @Date 2021/3/5 14:36
 * @Version 1.0
 * @Description 日志操作接口
 */
public interface LogOperateApi {
    /**
     * 写日志
     *
     * @param logs 日志
     */
    void write(List<LogModel> logs);

    /**
     * 读日志
     *
     * @return 日志列表
     */
    List<String> read();
}
