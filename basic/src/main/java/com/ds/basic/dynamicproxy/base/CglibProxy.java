package com.ds.basic.dynamicproxy.base;

import com.ds.basic.dynamicproxy.base.BaseCglibProxy;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author ds
 * @date 2023/4/14
 * @description
 */
public class CglibProxy extends BaseCglibProxy {
    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("CglibProxy...");
        return method.invoke(target, args);
    }
}
