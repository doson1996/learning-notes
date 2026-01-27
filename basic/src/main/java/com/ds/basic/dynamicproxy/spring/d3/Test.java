package com.ds.basic.dynamicproxy.spring.d3;

import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import org.springframework.context.support.GenericApplicationContext;

/**
 * @author ds
 * @date 2024/1/22
 * @description 代理对象创建时机
 * 创建 -> (*)依赖注入 -> 初始化 (*)
 * 依赖注入之前 （【循环依赖的时候】如果这里创建好了，后面就不会创建）
 * 初始化之后    【没有循环依赖的时候】
 * 注意： 在对象创建完成之前的依赖注入、初始化方法都应调用原始对象的方法
 */
public class Test {
    public static void main(String[] args) throws Exception {
        GenericApplicationContext context = new GenericApplicationContext();
//        context.registerBean(Aspect2.class);
        context.registerBean(ConfigurationClassPostProcessor.class);
        context.registerBean(AutowiredAnnotationBeanPostProcessor.class);
        context.registerBean(CommonAnnotationBeanPostProcessor.class);
        context.registerBean(MyAnnotationAwareAspectJAutoProxyCreator.class);
        context.registerBean(Config.class);
        context.refresh();

        IBean bean1 = context.getBean(IBean.class);
        System.out.println("bean1 = " + bean1.getClass());
        bean1.foo();
        bean1.say();

        Object beanA = context.getBean("myFactoryBean");
        Object myFactoryBean = context.getBean("&myFactoryBean");

//        A a = context.getBean(A.class);

        context.close();
    }
}
