package com.ds.basic.dynamicproxy.dljk.d2;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author ds
 * @date 2024/1/3
 * @description
 */
public interface InvocationHandler {

    Object invoke(Object proxy, Method method, Object[] args) throws Exception;

}
