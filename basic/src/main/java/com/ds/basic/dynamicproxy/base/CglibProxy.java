package com.ds.basic.dynamicproxy.base;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodProxy;

/**
 * @author ds
 * @date 2023/4/14
 * @description
 */
public class CglibProxy extends BaseCglibProxy {
    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("CglibProxy...");
        return method.invoke(target, args);
    }
}
