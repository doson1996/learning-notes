package com.ds.basic.dynamicproxy.dlcglib.d1;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * @author ds
 * @date 2024/1/15
 * @description
 */
public class Test {
    public static void main(String[] args) {
        Target target = new Target();
        Proxy proxy = new Proxy();
        proxy.setMethodInterceptor(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("before...");
                // return method.invoke(target, objects);      // 反射调用，需要被代理对象
                // return methodProxy.invoke(target, objects);   // 内部无反射，需要被代理对象
                return methodProxy.invokeSuper(o, objects);   // 内部无反射，需要代理对象
            }
        });
        proxy.save();
        proxy.save(1);
        proxy.save("2");
    }
}
