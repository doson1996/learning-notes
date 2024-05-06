package com.ds.springframework.chapter01.handler;

import com.ds.springframework.chapter01.bean.Person;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * @author ds
 * @date 2023/12/27
 * @description
 */
public class Test {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("ds.xml");
        Map personMap = context.getBean("person", Map.class);
        Person person1 = (Person) personMap.get(Mode.ASYNC);
        System.out.println("person1 = " + person1);
        person1.say();

        Person person2 = (Person) personMap.get(Mode.SYNC);
        System.out.println("person2 = " + person2);
        person2.say();
    }
}
