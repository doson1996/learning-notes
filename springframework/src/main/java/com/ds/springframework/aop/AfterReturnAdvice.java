package com.ds.springframework.aop;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;

/**
 * @author ds
 * @date 2025/5/29
 * @description
 */
public class AfterReturnAdvice implements AfterReturningAdvice {
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println("after invoke method [" + method.getName() + "],aop afterReturning logic invoked");
    }
}
