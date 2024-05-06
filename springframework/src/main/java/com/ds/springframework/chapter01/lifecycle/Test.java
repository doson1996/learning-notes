package com.ds.springframework.chapter01.lifecycle;

import org.springframework.aop.aspectj.autoproxy.AspectJAwareAdvisorAutoProxyCreator;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author ds
 * @date 2024/2/8
 * @description
 */
public class Test {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                AspectJAwareAdvisorAutoProxyCreator.class,
                ContextConfig.class);

        A a = (A) context.getBean("a");
        System.out.println("a.getClass() = " + a.getClass());
        a.say();
    }
}
