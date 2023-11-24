package com.ds;

import com.ds.config.MyBatisConfiguration;
import com.ds.controller.UserController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author ds
 */
public class Application {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyBatisConfiguration.class);
        UserController controller = context.getBean(UserController.class);
        System.out.println(controller.qryUser());
    }

}
