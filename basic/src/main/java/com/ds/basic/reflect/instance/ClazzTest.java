package com.ds.basic.reflect.instance;

/**
 * @author ds
 * @date 2023/8/7
 * @description 获取 Class 对象的四种方式
 */
public class ClazzTest {
    public static void main(String[] args) throws ClassNotFoundException {
        // 不会进行初始化
        Class<User> clazz1 = User.class;

        Class<?> clazz2 = Class.forName("com.ds.basic.reflect.instance.User");

        User user = new User();
        Class<? extends User> clazz3 = user.getClass();

        //不会进行初始化，意味着不进行包括初始化等一系列步骤，静态代码块和静态对象不会得到执行
        Class<?> clazz4 = ClassLoader.getSystemClassLoader().loadClass("com.ds.basic.reflect.instance.User");
    }
}
