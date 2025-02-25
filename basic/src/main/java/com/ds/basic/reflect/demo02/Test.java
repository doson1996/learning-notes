package com.ds.basic.reflect.demo02;

import java.lang.reflect.Field;

/**
 * 通过反射获取字段值
 *
 * @author ds
 */
public class Test {
    public static void main(String[] args) throws Exception {
        User user = new User("张三", 20);
        Field[] fields = User.class.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            System.out.println(field.getName() + "=" + field.get(user));
        }
    }
}
