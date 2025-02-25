package com.ds.basic.dynamicproxy.spring.d2;

import java.util.List;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;

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

}
