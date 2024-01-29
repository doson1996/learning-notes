package com.ds.basic.dynamicproxy.spring.d5;

import org.springframework.aop.aspectj.autoproxy.AspectJAwareAdvisorAutoProxyCreator;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import org.springframework.context.support.GenericApplicationContext;

/**
 * @author ds
 * @date 2024/1/25
 * @description 动态调用
 */
public class Test {
    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();
        context.registerBean(ConfigurationClassPostProcessor.class);
        context.registerBean(AspectJAwareAdvisorAutoProxyCreator.class);
        context.registerBean(BeanConfig.class);
        context.refresh();

        Target target = context.getBean(Target.class);
        target.foo(1);
    }
}
