package com.ds.dp.proxy.simple;

/**
 * @Author ds
 * @Date 2021/3/16 11:26
 * @Description 抽象的目标接口，定义具体的目标对象和代理公用的接口
 */
public interface Subject {

    /**
     * 示意方法，一个抽象的请求方法
     */
    void request();
}
