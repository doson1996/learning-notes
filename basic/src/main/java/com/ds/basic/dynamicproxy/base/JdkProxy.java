package com.ds.basic.dynamicproxy.base;

import java.lang.reflect.InvocationHandler;

/**
 * @author ds
 * @date 2023/4/14
 * @description
 */
public class JdkProxy extends BaseJdkProxy {
    @Override
    protected InvocationHandler invocationHandler() {
        return (proxy, method, args) -> {
            System.out.println("jdk proxy...");
            Object res = method.invoke(target, args);
            return res;
        };
    }
}
