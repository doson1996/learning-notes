package com.ds.jvm.chapter01;

import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

/**
 * @author ds
 * @date 2021/6/23 21:04
 */
public class Demo18WeakReference {
    public static void main(String[] args) {
       WeakReference<User> sf = new WeakReference<>(new User(1, "zs"));
        System.out.println(sf.get());
        System.gc();
        //弱引用，遇到gc就回收 （看见即回收）
        System.out.println(sf.get());
   //     WeakHashMap
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
