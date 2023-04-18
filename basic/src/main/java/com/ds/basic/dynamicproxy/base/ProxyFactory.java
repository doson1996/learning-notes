package com.ds.basic.dynamicproxy.base;

/**
 * @author ds
 * @date 2023/4/14
 * @description
 */
public class ProxyFactory implements BaseProxy {

    private BaseProxy jdkProxy = new JdkProxy();

    private BaseProxy cglibProxy = new CglibProxy();

    @Override
    public Object create(Object target) {
        // 被代理对象没有实现接口或是一个接口的时候，用cglib代理
        if (target.getClass().getInterfaces().length == 0 || target.getClass().isInterface()) {
            return cglibProxy.create(target);
        }
        return jdkProxy.create(target);
    }
}
