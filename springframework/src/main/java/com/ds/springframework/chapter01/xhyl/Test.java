package com.ds.springframework.chapter01.xhyl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author ds
 */
public class Test {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("xhyl.xml");
//        A a = applicationContext.getBean("a",A.class);
//        System.out.println(a.getB());
//        B b = applicationContext.getBean("b",B.class);
//        System.out.println(b.getA());
        applicationContext.getBean("user",User.class);
    }
}
