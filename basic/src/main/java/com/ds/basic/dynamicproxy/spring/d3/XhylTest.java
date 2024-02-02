package com.ds.basic.dynamicproxy.spring.d3;

import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import org.springframework.context.support.GenericApplicationContext;

/**
 * @author ds
 * @date 2024/1/28
 * @description
 */
public class XhylTest {
    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();
        context.registerBean(Aspect2.class);
        context.registerBean(ConfigurationClassPostProcessor.class);
        context.registerBean(AutowiredAnnotationBeanPostProcessor.class);
        context.registerBean(CommonAnnotationBeanPostProcessor.class);
        context.registerBean(MyAnnotationAwareAspectJAutoProxyCreator.class);
        context.registerBean("a", A.class);
        context.registerBean("b", B.class);
        context.refresh();

        A a = context.getBean(A.class);
        System.out.println("a = " + a.getClass());
        a.foo();
        context.close();
    }
}
