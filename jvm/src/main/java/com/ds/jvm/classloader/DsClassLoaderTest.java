package com.ds.jvm.classloader;

/**
 * @author ds
 */
public class DsClassLoaderTest {

    public static void main(String[] args) throws ClassNotFoundException {
        DsClassLoader classLoader = new DsClassLoader("E:/");
        Class<?> a = classLoader.findClass("A");
        System.out.println("a = " + a.getClassLoader().getClass().getName());
    }
}
