package com.ds;

import com.ds.config.MyBatisConfiguration;
import com.ds.controller.UserController;
import com.ds.mapper.UserMapper;
import com.ds.mybatisx.spring.mapper.MapperxFactoryBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyBatisConfiguration.class);
        UserController controller = context.getBean(UserController.class);
        System.out.println(controller.qryUser());
    }
}
