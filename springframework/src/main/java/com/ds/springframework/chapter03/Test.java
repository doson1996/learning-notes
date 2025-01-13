package com.ds.springframework.chapter03;

/**
 * @author ds
 * @date 2024/11/16 0:47
 */
public class Test {
    public static void main(String[] args) {
        BeanDefinition beanDefinition = new BeanDefinition(new UserService());
        BeanFactory beanFactory = new BeanFactory();
        beanFactory.registerBeanDefinition("userService", beanDefinition);
        Object userService = beanFactory.getBean("userService");
        System.out.println("userService = " + userService);
    }
}
