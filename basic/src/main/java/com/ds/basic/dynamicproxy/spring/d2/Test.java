package com.ds.basic.dynamicproxy.spring.d2;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import org.springframework.context.support.GenericApplicationContext;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @author ds
 * @date 2024/1/22
 * @description
 */
public class Test {
    public static void main(String[] args) throws Exception {
        GenericApplicationContext context = new GenericApplicationContext();
        context.registerBean(Aspect1.class);
        context.registerBean(Config.class);
        context.registerBean(ConfigurationClassPostProcessor.class);
        context.registerBean(AnnotationAwareAspectJAutoProxyCreator.class);
        context.refresh();

        for (String name : context.getBeanDefinitionNames()) {
            // System.out.println("name = " + name);
        }


        AnnotationAwareAspectJAutoProxyCreator creator = context.getBean(AnnotationAwareAspectJAutoProxyCreator.class);
        Method findEligibleAdvisors = creator.getClass().getSuperclass().getSuperclass().getDeclaredMethod("findEligibleAdvisors", Class.class, String.class);
        findEligibleAdvisors.setAccessible(true);

        List<Advisor> advisors = (List<Advisor>) findEligibleAdvisors.invoke(creator, Target2.class, "target1");
        for (Advisor advisor : advisors) {
            System.out.println("advisor = " + advisor);
        }
    }
}
