package com.ds;

import com.ds.springframework.ciz.beans.factory.config.BeanDefinition;
import com.ds.springframework.ciz.beans.factory.support.DefaultListableBeanFactory;
import com.ds.springframework.ciz.beans.service.UserService;

/**
 * @author ds
 * @date 2025/1/16
 * @description
 */
public class Test {

    public static void main(String[] args) {
        test_BeanFactory();
    }

    public static void test_BeanFactory() {
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 2.注册 bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService", beanDefinition);
        // 3.第一次获取 bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();
        // 4.第二次获取 bean from Singleton
        UserService userService_singleton = (UserService) beanFactory.getBean("userService");
        userService_singleton.queryUserInfo();
    }

}
