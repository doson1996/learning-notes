package com.ds.basic.reflect.demo01;

import com.ds.basic.util.reflect.ReflectUtil;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * @author ds
 */
public class Test {

    public static void main(String[] args) throws IllegalAccessException {
        testC();
    }

    /**
     * 没有继承的时候
     */
    private static void testA() {
        A a = new A();
        a.setA("aaa");
        Map<String, Object> a1 = ReflectUtil.parseObject(a.getClass(), a);
        System.out.println("a1 = " + a1);
        Map<String, Object> a2 = ReflectUtil.parseObjectAndParent(a.getClass(), a);
        System.out.println("a2 = " + a2);
    }

    /**
     * 一层继承的时候
     */
    private static void testB() {
        B b = new B();
        b.setA("aaa");
        b.setB("bbb");
        Map<String, Object> b1 = ReflectUtil.parseObject(b.getClass(), b);
        System.out.println("b1 = " + b1);
        Map<String, Object> b2 = ReflectUtil.parseObjectAndParent(b.getClass(), b);
        System.out.println("b2 = " + b2);
    }

    private static void testC() {
        C c = new C();
        c.setA("aaa");
        c.setB("bbb");
        Map<String, Object> c1 = ReflectUtil.parseObject(c.getClass(), c);
        System.out.println("c1 = " + c1);
        Map<String, Object> c2 = ReflectUtil.parseObjectAndParent(c.getClass(), c);
        System.out.println("c2 = " + c2);
    }

}
