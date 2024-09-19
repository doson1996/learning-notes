package com.ds.basic.reflect.instance;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/**
 * @author ds
 */
public class Client {

    public static void main(String[] args) throws Exception {
        Constructor<User> constructor = User.class.getDeclaredConstructor(null);
        constructor.setAccessible(true);
        User user = constructor.newInstance(null);
        user.say();

        Field[] declaredFields = User.class.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println("declaredField = " + declaredField);
            Class<?> type = declaredField.getType();
            System.out.println("type = " + type);

            Class<?> declaringClass = declaredField.getDeclaringClass();
            System.out.println("declaringClass = " + declaringClass);
        }

    }

}
