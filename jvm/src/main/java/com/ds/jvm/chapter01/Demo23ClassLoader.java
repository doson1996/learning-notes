package com.ds.jvm.chapter01;

/**
 * @author ds
 * @date 2021/9/14 22:08
 */
public class Demo23ClassLoader {
    public static void main(String[] args) throws Exception {

        //系统类加载器 sun.misc.Launcher$AppClassLoader@18b4aac2
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println("systemClassLoader = " + systemClassLoader);

        //扩展类加载器 sun.misc.Launcher$ExtClassLoader@1b6d3586
        ClassLoader extClassLoader = systemClassLoader.getParent();
        System.out.println("extClassLoader = " + extClassLoader);

        //引导类加载器 null
        ClassLoader bootstrapClassLoader = extClassLoader.getParent();
        System.out.println("bootstrapClassLoader = " + bootstrapClassLoader);

        //引用类型数组根据数组类型的类加载器   String是由引导类加载器加载，所以为null
        String[] strArr = new String[10];
        System.out.println("strArr = " + strArr.getClass().getClassLoader());

        //基本类型数组不需要类加载器加载，所以为null
        int[] intArr = new int[10];
        System.out.println("intArr = " + intArr.getClass().getClassLoader());

        Class<?> user1 = Class.forName("com.ds.jvm.chapter01.User");
        System.out.println("user1 = " + user1);
       // user1.getMethod("setName", String.class).invoke();


    }

}
