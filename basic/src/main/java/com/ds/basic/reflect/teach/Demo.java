package com.ds.basic.reflect.teach;

import com.ds.basic.reflect.teach.entity.Person;
import com.ds.basic.reflect.teach.entity.User;

/**
 * @author ds
 * @date 2024/11/13 20:54
 */
public class Demo {
    public static void main(String[] args) throws ClassNotFoundException {
        User user = new User("张三");
        user.sayHello();
        System.out.println("user = " + user);

        Person person = new Person("李四");
        person.sayHello();

        Class.forName("com.ds.basic.reflect.teach.entity.Person");

//        Mapper userMapper = new UserMapper();
//        userMapper.insert(user);
//
//        Person person = new Person("李四");
//        Mapper personMapper = new UserMapper();
//        personMapper.insert(person);
    }

}
