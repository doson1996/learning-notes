package com.ds.basic.dynamicproxy.dljk.d2;

import java.lang.reflect.Method;

/**
 * @author ds
 * @date 2024/1/3
 * @description
 */
public interface InvocationHandler {

    Object invoke(Method method, Object[] args);

}
