package com.ds.basic.dynamicproxy.dljk.d2;

import lombok.SneakyThrows;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author ds
 * @date 2024/1/3
 * @description
 */
public class TestProxy {
    public static void main(String[] args) {
        ITarget target = new $Proxy0(new InvocationHandler() {
            @SneakyThrows
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) {
                System.out.println("before...");
                return method.invoke(new Target(), args);
            }
        });

        target.say();
    }
}
