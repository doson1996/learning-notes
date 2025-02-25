package com.ds.dp.strategy.example1;

/**
 * @Author ds
 * @Date 2021/3/31 10:46
 * @Description 日志记录策略接口
 */
public interface LogStrategy {

    /**
     * 写日志
     *
     * @param log
     */
    void write(String log);
}
