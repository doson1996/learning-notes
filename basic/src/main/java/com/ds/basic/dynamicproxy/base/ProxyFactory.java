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
        if (target.getClass().getInterfaces().length == 0) {
            return cglibProxy.create(target);
        }
        return jdkProxy.create(target);
    }
}
