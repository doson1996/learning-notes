package com.ds.concurrent.chapter02;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @Author ds
 * @Date 2021/4/19 14:10
 * @Description 原子更新基本类型的 AtomicInteger，只能更新一个变量，如果要原子更新多个变量，
 * 就需要使用这个原子更新引用类型提供的类。 Atomic 包提供了以下 3 个类。
 * ⚫ AtomicReference：原子更新引用类型。
 * ⚫ AtomicReferenceFieldUpdater：原子更新引用类型里的字段。
 * ⚫ AtomicMarkableReference：原子更新带有标记位的引用类型。可以原子更新一个布尔类型的标记位和引用类型。
 * 构造方法是 AtomicMarkableReference（ V initialRef， boolean initialMark）
 */
public class Demo20AtomicReference {

    public static void main(String[] args) {
        AtomicReference<User> ar = new AtomicReference<>();
        User user = new User("张三", 10);
        ar.set(user);

        User newUser = new User("李四", 20);
        ar.compareAndSet(user, newUser);

        System.out.println("ar.get() = " + ar.get());
    }


    private static class User {

        private String name;

        private int age;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
