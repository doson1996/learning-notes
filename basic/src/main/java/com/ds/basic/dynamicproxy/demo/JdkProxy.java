package com.ds.basic.dynamicproxy.demo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author ds
 * @Date 2021/3/19 9:39
 * @Description jdk动态代理
 */
public class JdkProxy implements InvocationHandler {

    private Object target;


    public Object newProxy(Object target){
        this.target = target;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("代理方法执行之前--------------");
        Object invoke = method.invoke(target, args);
        System.out.println("代理方法执行之后--------------");
        return invoke;
    }
}
