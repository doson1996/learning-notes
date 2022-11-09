package com.ds.basic.reflect.instance;

import java.lang.reflect.Constructor;

/**
 * @author ds
 */
public class Client {

    public static void main(String[] args) throws Exception {
        Constructor<User> constructor = User.class.getDeclaredConstructor(null);
        constructor.setAccessible(true);
        User user = constructor.newInstance(null);
        user.say();
    }

}
