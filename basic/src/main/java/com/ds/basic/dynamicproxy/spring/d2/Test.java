package com.ds.basic.dynamicproxy.spring.d2;

import java.util.List;

import org.springframework.aop.Advisor;
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
        context.registerBean(Aspect1.class);
        context.registerBean(Config.class);
        context.registerBean(ConfigurationClassPostProcessor.class);
        context.registerBean(MyAnnotationAwareAspectJAutoProxyCreator.class);
        context.refresh();
        for (String name : context.getBeanDefinitionNames()) {
            System.out.println("name = " + name);
        }
        System.out.println("------------------------bean----------------------");

        MyAnnotationAwareAspectJAutoProxyCreator creator = context.getBean(MyAnnotationAwareAspectJAutoProxyCreator.class);
        /*
            第一个重要方法 findEligibleAdvisors,找到有资格的advisor
                a. 高级的 Aspect1 （也会转换为低级）
                b. 低级的 Config$advice2
         */
        List<Advisor> advisors = creator.findEligibleAdvisors(Target1.class, "target1");
        for (Advisor advisor : advisors) {
            System.out.println("advisor = " + advisor);
        }
        System.out.println("------------------------advisor----------------------");


        /*
            第二个重要方法wrapIfNecessary，内部调用findEligibleAdvisors，只要不返回为空，就创建代理对象
         */
        Object o1 = creator.wrapIfNecessary(new Target1(), "target1", "target1");
        Object o2 = creator.wrapIfNecessary(new Target2(), "target2", "target2");

        System.out.println("o1 = " + o1.getClass());
        System.out.println("o2 = " + o2.getClass());

        ((ITarget) o1).foo();
        ((Target2) o2).say();
    }
}
