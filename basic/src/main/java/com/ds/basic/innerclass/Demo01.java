package com.ds.basic.innerclass;

/**
 * @Author ds
 * @Date 2021/3/17 9:33
 * @Description 静态嵌套类和嵌套类
 */
public class Demo01 {

    static String smsg = "Demo01 smsg";

    private String msg = "Demo01";

    public static void main(String[] args) {

        Demo01 demo01 = new Demo01();
        demo01.say();

        Demo01.StaticInnerClass.say();

    }

    public void say() {
        new InnerClass().say();
    }


    private class InnerClass {

        public void say() {
            System.out.println("InnerClass" + msg);
        }
    }

    public static class StaticInnerClass {

        public static void say() {
            System.out.println("InnerClass" + smsg);
        }
    }
}
