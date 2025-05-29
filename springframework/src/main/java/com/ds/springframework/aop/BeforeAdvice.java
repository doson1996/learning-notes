package com.ds.springframework.aop;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

/**
 * @author ds
 * @date 2025/5/29
 * @description
 */
public class BeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("before invoke method [" + method.getName() + "],aop before logic invoked");
    }
}
