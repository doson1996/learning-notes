package com.ds.jvm.classloader;

/**
 * @Author ds
 * @Date 2021/4/16 14:31
 * @Description     双亲委派
 * @see {@link ClassLoader}  --> loadClass
 *
 *          作用：
 *              1.避免重复加载
 *              2.防止核心类（如String）被篡改，保证安全性  (具体例子：可以看我自定义的 java.lang包下的String)
 *
 *         首先,检查该类是否已经加载，如果没有加载，会一直向上委托加载，
 *         最后委托到BootstrapClassLoader （c/c++写的），如果还没有加载，就自己加载 （ c = findClass(name);）
 *
 *         Bootstrap ClassLoader 启动类加载器
 *         Extention ClassLoader 标准扩展类加载器
 *         Application ClassLoader 应用类加载器
 *         User ClassLoader 用户自定义类加载器
 *
 *         打破双亲委派： 自定义一个类加载器的时候，并重写loadClass 或 findclass(推荐)方法 。
 *
 *         打破双亲委派的例子：jdbc 、tomcat 、jdk9模块化技术
 */
public class ParentalDelegation {

    public static void main(String[] args) {

        //sun.misc.Launcher$AppClassLoader@18b4aac2加载了Demo01
        System.out.println(ParentalDelegation.class.getClassLoader() + "加载了ParentalDelegation");

        //null加载了ParentalDelegation  由BootstrapClassLoader加载;
        System.out.println(String.class.getClassLoader() + "加载了String ");


        /**
         * 避免重复加载
         * sun.misc.Launcher$AppClassLoader@18b4aac2加载了A  class com.ds.jvm.classloader.A
         * sun.misc.Launcher$AppClassLoader@18b4aac2加载了A  class com.ds.jvm.classloader.A
         */
        A a = new A();
        System.out.println(a.getClass().getClassLoader() + "加载了A  " + a.getClass());
        B b = new B();
        System.out.println(b.a.getClass().getClassLoader() + "加载了A  " + b.a.getClass());
    }
}
