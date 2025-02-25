package com.ds.basic.dynamicproxy.demo;


import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * @Author ds
 * @Date 2021/3/19 9:40
 * @Description
 */
public class CglibProxy implements MethodInterceptor {

    private Object target;

    public Object newProxy(Object target) {
        this.target = target;
        //1.创建Enhancer
        Enhancer enhancer = new Enhancer();
        //2.传递目标对象的class
        enhancer.setSuperclass(target.getClass());
        //3.设置回调操作
        enhancer.setCallback(this);

        return enhancer.create();
    }

    @Override
    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("代理方法执行之前--------------");
        Object invoke = methodProxy.invoke(target, args);
        System.out.println("代理方法执行之后--------------");
        return invoke;
    }

}
