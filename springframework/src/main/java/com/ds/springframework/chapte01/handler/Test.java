package com.ds.springframework.chapte01.handler;

import com.ds.springframework.chapte01.bean.Person;
import com.ds.springframework.chapte01.bean.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author ds
 * @date 2023/12/27
 * @description
 */
public class Test {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("ds.xml");
        Person person = (Person) context.getBean("person");
        System.out.println("person = " + person);
    }
}
