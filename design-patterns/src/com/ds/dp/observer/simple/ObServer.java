package com.ds.dp.observer.simple;

/**
 * @Author ds
 * @Date 2021/3/17 11:17
 * @Description
 */
public interface ObServer {

    /**
     * 更新的接口
     * @param subject
     */
    public void update(Subject subject);
}
