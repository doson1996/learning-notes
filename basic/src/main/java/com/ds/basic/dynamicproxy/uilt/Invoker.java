package com.ds.basic.dynamicproxy.uilt;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author ds
 */
public class Invoker<T> implements InvocationHandler {

    private T target;

    public Invoker(T target) {
        this.target = target;
    }

    public T newProxy() {
        return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(target, args);
    }
}
