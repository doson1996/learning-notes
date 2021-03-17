package com.ds.basic.innerclass;

/**
 * @Author ds
 * @Date 2021/3/17 10:20
 * @Description
 */
public class Test {

    public static void main(String[] args) {

       // Demo01.StaticInnerClass.say();

        Singleton instance1 = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();
        System.out.println(instance1 == instance2);
    }
}
