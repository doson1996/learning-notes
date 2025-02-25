package com.ds.basic.dynamicproxy.dljdk.d2;

import java.lang.reflect.Method;

import lombok.SneakyThrows;

/**
 * @author ds
 * @date 2024/1/3
 * @description
 */
public class $Proxy0 implements ITarget {

    private final InvocationHandler h;

    private static final Method m1;

    public $Proxy0(InvocationHandler h) {
        this.h = h;
    }

    @SneakyThrows
    @Override
    public void say() {
        h.invoke(this, m1, new Object[]{});
    }

    static {
        try {
            m1 = ITarget.class.getMethod("say");
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
