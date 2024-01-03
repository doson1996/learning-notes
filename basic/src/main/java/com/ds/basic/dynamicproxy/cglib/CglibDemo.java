package com.ds.basic.dynamicproxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author ds
 * @date 2024/1/3
 * @description
 */
public class CglibDemo {
    public static void main(String[] args) {
        Target target = new Target();
        Target proxyObj = (Target) Enhancer.create(Target.class, new MethodInterceptor() {
            @Override
            public Object intercept(Object proxyObject, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                System.out.println("before...");
                //    Object result = method.invoke(target, args); // 通过反射调用
                Object result = methodProxy.invoke(target, args);   // 内部没有用反射，需要目标对象 （spring采用这种方式）
                //    Object result = methodProxy.invokeSuper(proxyObject, args);  // 通过反射调用，需要代理对象
                System.out.println("after...");
                return result;
            }
        });

        proxyObj.say();
    }

    /**
     * final修饰的类，不能被代理
     */
    static class Target {

        // final修饰的方法，不能被增强
        public void say() {
            System.out.println("target say...");
        }
    }

}
