package com.ds.basic.dynamicproxy.dljk.d3;

import lombok.SneakyThrows;
import sun.reflect.MethodAccessor;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author ds
 * @date 2024/1/4
 * @description
 */
public class UserServiceImpl implements IUserService {

    public static void say(int i) {
        System.out.println("say = " + i);
    }

    @SneakyThrows
    public static void show(int i, Method m) {
        Field methodAccessorF = Method.class.getDeclaredField("methodAccessor");
        methodAccessorF.setAccessible(true);
        Object o = methodAccessorF.get(m);
        System.out.println(i + " = " + o);
    }

    public static void main(String[] args) throws Exception {
        Method say = UserServiceImpl.class.getMethod("say", int.class);
        for (int i = 0; i < 18; i++) {
            show(i, say);
            say.invoke(null, i);
        }
    }

}
