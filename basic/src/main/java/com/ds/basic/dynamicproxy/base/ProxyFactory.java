package com.ds.basic.dynamicproxy.base;

/**
 * @author ds
 * @date 2023/4/14
 * @description 动态代理工厂类，根据目标对象特性选择合适的代理方式
 */
public class ProxyFactory implements BaseProxy {

    private final BaseProxy jdkProxy = new JdkProxy();

    private final BaseProxy cglibProxy = new CglibProxy();

    @Override
    public Object create(Object target) {
        if (target == null) {
            throw new IllegalArgumentException("目标对象不能为空");
        }

        Class<?> targetClass = target.getClass();
        int interfaceCount = targetClass.getInterfaces().length;

        // 判断是否使用 CGLIB 代理
        if (interfaceCount == 0 || targetClass.isInterface()) {
            return cglibProxy.create(target);
        }

        // 默认使用 JDK 动态代理
        return jdkProxy.create(target);
    }
}
