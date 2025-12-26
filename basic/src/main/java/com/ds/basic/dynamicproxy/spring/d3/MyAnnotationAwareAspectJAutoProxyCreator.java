package com.ds.basic.dynamicproxy.spring.d3;

import java.util.List;

import org.springframework.aop.Advisor;
import org.springframework.aop.TargetSource;
import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;

/**
 * @author ds
 * @date 2024/1/22
 * @description
 */
public class MyAnnotationAwareAspectJAutoProxyCreator extends AnnotationAwareAspectJAutoProxyCreator {

    @Override
    protected List<Advisor> findCandidateAdvisors() {
        List<Advisor> candidateAdvisors = super.findCandidateAdvisors();
        if (!candidateAdvisors.isEmpty()) {
            System.out.println("candidateAdvisors = " + candidateAdvisors);
        }
        return candidateAdvisors;
    }

    @Override
    public List<Advisor> findEligibleAdvisors(Class<?> beanClass, String beanName) {
        System.out.println(beanName + " findEligibleAdvisors...");
        List<Advisor> eligibleAdvisors = super.findEligibleAdvisors(beanClass, beanName);
        if (!eligibleAdvisors.isEmpty()) {
            System.out.println(beanName + " eligibleAdvisors = " + eligibleAdvisors);
        }
        return eligibleAdvisors;
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
