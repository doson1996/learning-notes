package com.ds.basic.dynamicproxy.base;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author ds
 * @date 2023/4/14
 * @description jdk代理
 */
public abstract class BaseJdkProxy implements BaseProxy {

    /**
     * 被代理的对象
     */
    protected Object target;

    /**
     * 创建代理对象
     *
     * @return
     */
    @Override
    public Object create(Object target) {
        this.target = target;
        return Proxy.newProxyInstance(this.target.getClass().getClassLoader(),
                this.target.getClass().getInterfaces(),
                invocationHandler());
    }

    protected abstract InvocationHandler invocationHandler();

}
