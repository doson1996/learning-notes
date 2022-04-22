package com.ds.basic.util.reflect;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * 反射工具类
 * @author ds
 */

public class ReflectUtil {

    /**
     * 禁止实例化
     */
    private ReflectUtil() {

    }

    /**
     * 解析当前类和所有父类的字段和值
     * @param clazz
     * @param object
     * @return Map<字段,值>
     */
    public static Map<String,Object> parseObjectAndParent(Class clazz, Object object) {
        Map<String,Object> result = new HashMap<>();
        //当前类的字段和值
        Map<String, Object> current = parseObject(clazz, object);
        result.putAll(current);
        //所有父类的字段和值
        if (Object.class != clazz.getSuperclass()) {
            result.putAll(parseObjectAndParent(clazz.getSuperclass(), object));
        }
        return result;
    }

    /**
     * 解析当前类的字段和值
     * @param clazz
     * @param object
     * @return Map<字段,值>
     */
    public static Map<String,Object> parseObject(Class clazz, Object object) {
        Map<String,Object> result = new HashMap<>();
        //存在继承的时候，无法反射出当前类父类中的字段
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            Object value = null;
            try {
                value = field.get(object);
            } catch (IllegalAccessException e) {
                System.out.println("解析发生异常:" + e);
            }
            result.put(field.getName(), value);
        }

        return result;
    }


}
