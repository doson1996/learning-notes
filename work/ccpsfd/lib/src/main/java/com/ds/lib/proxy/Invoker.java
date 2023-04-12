package com.ds.lib.proxy;

import com.ds.lib.base.AbstractBaseService;
import com.ds.lib.context.SpringContext;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

/**
 * @author ds
 * @date 2023/4/12
 * @description
 */
public class Invoker implements InvocationHandler {

    /**
     * 被代理的接口
     */
    private Class target;

    public Invoker(Class clazz) {
        this.target = clazz;
    }

    /**
     * 返回接口的代理对象
     *
     * @return
     */
    public Object getInstance() {
        return Proxy.newProxyInstance(target.getClassLoader(), new Class[]{target}, this);
    }

    /**
     * 请求转发实现
     *
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object arg = args[0];
        AbstractBaseService service = (AbstractBaseService) SpringContext.getContext().getBean(method.getName());
        return service.doTrans((Map<String, Object>) arg);
    }
}
