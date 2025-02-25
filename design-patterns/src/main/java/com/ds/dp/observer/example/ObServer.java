package com.ds.dp.observer.example;

/**
 * @Author ds
 * @Date 2021/3/17 11:48
 * @Description
 */
public interface ObServer {

    /**
     * 观察到有新报纸
     *
     * @param subject
     */
    void update(Subject subject);
}
