package com.ds.basic.dynamicproxy.base;

/**
 * @author ds
 * @date 2023/4/14
 * @description
 */
public interface BaseProxy {

    /**
     * 创建代理对象
     *
     * @param target
     * @return
     */
    Object create(Object target);

}
