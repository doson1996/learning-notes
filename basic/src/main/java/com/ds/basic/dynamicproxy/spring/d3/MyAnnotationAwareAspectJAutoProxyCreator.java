package com.ds.basic.dynamicproxy.spring.d3;

import org.springframework.aop.Advisor;
import org.springframework.aop.TargetSource;
import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;

import java.util.List;

/**
 * @author ds
 * @date 2024/1/22
 * @description
 */
public class MyAnnotationAwareAspectJAutoProxyCreator extends AnnotationAwareAspectJAutoProxyCreator {

    @Override
    public List<Advisor> findEligibleAdvisors(Class<?> beanClass, String beanName) {
        return super.findEligibleAdvisors(beanClass, beanName);
    }

    @Override
    public Object wrapIfNecessary(Object bean, String beanName, Object cacheKey) {
        return super.wrapIfNecessary(bean, beanName, cacheKey);
    }

    @Override
    protected Object createProxy(Class<?> beanClass, String beanName, Object[] specificInterceptors, TargetSource targetSource) {
        System.out.println(beanName + "创建代理对象...");
        return super.createProxy(beanClass, beanName, specificInterceptors, targetSource);
    }

}
