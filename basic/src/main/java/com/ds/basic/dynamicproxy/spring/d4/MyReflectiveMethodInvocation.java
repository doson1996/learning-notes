package com.ds.basic.dynamicproxy.spring.d4;

import java.lang.reflect.Method;
import java.util.List;

import org.springframework.aop.framework.ReflectiveMethodInvocation;

/**
 * @author ds
 * @date 2024/1/25
 * @description
 */
public class MyReflectiveMethodInvocation extends ReflectiveMethodInvocation {

    /**
     * Construct a new ReflectiveMethodInvocation with the given arguments.
     *
     * @param proxy                                the proxy object that the invocation was made on
     * @param target                               the target object to invoke
     * @param method                               the method to invoke
     * @param arguments                            the arguments to invoke the method with
     * @param targetClass                          the target class, for MethodMatcher invocations
     * @param interceptorsAndDynamicMethodMatchers interceptors that should be applied,
     *                                             along with any InterceptorAndDynamicMethodMatchers that need evaluation at runtime.
     *                                             MethodMatchers included in this struct must already have been found to have matched
     *                                             as far as was possibly statically. Passing an array might be about 10% faster,
     *                                             but would complicate the code. And it would work only for static pointcuts.
     */
    protected MyReflectiveMethodInvocation(Object proxy, Object target, Method method, Object[] arguments, Class<?> targetClass, List<Object> interceptorsAndDynamicMethodMatchers) {
        super(proxy, target, method, arguments, targetClass, interceptorsAndDynamicMethodMatchers);
    }

}
