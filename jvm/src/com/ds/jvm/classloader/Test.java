package com.ds.jvm.classloader;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author ds
 * @Date 2021/4/19 23:56
 * @Description
 */
public class Test {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        A a = new A();
        System.out.println(a.getClass().getClassLoader());

        ClassLoader classLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                InputStream stream = getClass().getResourceAsStream(fileName);
                if (stream == null) {
                    return super.loadClass(name);
                }
                try {
                    byte[] b = new byte[stream.available()];
                    // 将流写入字节数组b中
                    stream.read(b);
                    return defineClass(name, b, 0, b.length);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return super.loadClass(name);
            }
        };

        Class<?> clazz = classLoader.loadClass("com.ds.jvm.classloader.A");
        System.out.println(clazz.getClassLoader());
        Object o =  clazz.newInstance();
        System.out.println(o instanceof A);
    }
}
