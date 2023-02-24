package com.ds.jvm.chapter01;

import java.lang.ref.SoftReference;

/**
 * -Xms10m -Xmx10m -XX:+PrintGCDetails
 * @author ds
 * @date 2021/6/23 20:52
 */
public class Demo18SoftReference {

    public static void main(String[] args) {
        SoftReference<User> sf = new SoftReference<>(new User(1, "zs"));
        System.out.println(sf.get());
        System.gc();
        System.out.println(sf.get());
        try {
            //使内存不足
            byte[] b = new byte[1024 * 1024 * 7];
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //在内存紧张、不够的时候会回收软引用对象
            System.out.println(sf.get());
        }

    }

    private static class User {

        private int id;

        private String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
