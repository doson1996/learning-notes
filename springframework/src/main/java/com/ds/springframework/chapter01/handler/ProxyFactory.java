package com.ds.springframework.chapter01.handler;

import cn.hutool.core.thread.NamedThreadFactory;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author ds
 * @date 2023/12/27
 * @description
 */
public class ProxyFactory implements MethodInterceptor {

    private static final ProxyFactory INSTANCE = new ProxyFactory();

    private static final int DEFAULT_NUM = 2;

    private static final ThreadPoolExecutor POOL = new ThreadPoolExecutor(
            DEFAULT_NUM,
            Math.max(DEFAULT_NUM, Runtime.getRuntime().availableProcessors()),
            60,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(1000),
            new NamedThreadFactory("ds", false)
    );

    private ProxyFactory() {

    }

    public static ProxyFactory getInstance() {
        return INSTANCE;
    }

    /**
     * 被代理对象
     */
    private Object target;

    public Object create(Object target) {
        this.target = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Exception {
        POOL.execute(() -> {
            try {
                method.invoke(target, args);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return null;
    }

}
