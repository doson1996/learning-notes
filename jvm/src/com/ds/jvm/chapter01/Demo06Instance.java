package com.ds.jvm.chapter01;

/**
 * @Author ds
 * @Date 2021/4/26 23:13
 * @Description
 */
public class Demo06Instance {
    public static void main(String[] args) throws Exception {

        User user = new User();

        /**
         * 只能调用空参构造方法，权限必须是public，不建议使用
         */
        user = User.class.newInstance();

        /**
         * 可以调用空参、带参构造方法，权限没有要求
         */
        User zs = User.class.getConstructor(int.class, String.class).newInstance(1, "张三");
        System.out.println("zs = " + zs);
    }

}
