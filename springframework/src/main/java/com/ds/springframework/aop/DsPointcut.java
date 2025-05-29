package com.ds.springframework.aop;

import java.lang.reflect.Method;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.lang.NonNull;

/**
 * @author ds
 * @date 2025/5/29
 * @description
 */
public class DsPointcut implements Pointcut {
    @Override
    public ClassFilter getClassFilter() {
        // 在类级别上不进行拦截
        return ClassFilter.TRUE;
    }

    @Override
    public MethodMatcher getMethodMatcher() {
        return new StaticMethodMatcherPointcut() {
            @Override
            public boolean matches(@NonNull Method method, Class<?> targetClass) {
                // 对于toString方法不进行拦截
                return !"toString".equals(method.getName());
            }
        };
    }
}
