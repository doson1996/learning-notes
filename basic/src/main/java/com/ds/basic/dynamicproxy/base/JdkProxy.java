package com.ds.basic.dynamicproxy.base;

import com.ds.basic.dynamicproxy.base.BaseJdkProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

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
