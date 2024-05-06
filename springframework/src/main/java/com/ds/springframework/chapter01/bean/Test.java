package com.ds.springframework.chapter01.bean;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        AbstractBeanDefinition personBeanDefinition = BeanDefinitionBuilder.genericBeanDefinition().getBeanDefinition();
        personBeanDefinition.setBeanClass(Person.class);
        MutablePropertyValues propertyValues = new MutablePropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("id", 1));
        propertyValues.addPropertyValue(new PropertyValue("name", "张三"));
        personBeanDefinition.setPropertyValues(propertyValues);
        context.registerBeanDefinition("person", personBeanDefinition);

        AbstractBeanDefinition userServiceBeanDefinition = BeanDefinitionBuilder.genericBeanDefinition().getBeanDefinition();
        userServiceBeanDefinition.setBeanClass(UserServiceFactoryBean.class);
        context.registerBeanDefinition("userService", userServiceBeanDefinition);

        context.refresh();

        Person person = context.getBean("person", Person.class);
        System.out.println("person = " + person);

        System.out.println(context.getBean("userService"));

        System.out.println(context.getBean("&userService"));
    }
}
