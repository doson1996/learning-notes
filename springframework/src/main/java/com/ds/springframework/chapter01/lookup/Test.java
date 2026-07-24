package com.ds.springframework.chapter01.lookup;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author ds
 * @date 2024/4/16
 * @description 1.spring在创建bean时判断有没有@Lookup注解，有的话就把方法封装成LookupOverride加到BeanDefinition的MethodOverrides {@link org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor#determineCandidateConstructors(java.lang.Class, java.lang.String)}
 *              2.在实例化时判断有没有MethodOverrides，有的话进行代理 {@link org.springframework.beans.factory.support.SimpleInstantiationStrategy#instantiate(org.springframework.beans.factory.support.RootBeanDefinition, java.lang.String, org.springframework.beans.factory.BeanFactory)}
 */
public class Test {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        for (int i = 0; i < 2; i++) {
            Single single = context.getBean(Single.class);
            single.say();
        }
    }
}
