package com.ds.springframework.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author ds
 * @date 2025/5/29
 * @description
 */
public class AroundAdvice implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("aroundAdvice invoked");
        return invocation.proceed();
    }
}
