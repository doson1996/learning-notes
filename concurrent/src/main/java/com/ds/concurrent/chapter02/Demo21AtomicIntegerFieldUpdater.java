package com.ds.concurrent.chapter02;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @Author ds
 * @Date 2021/4/19 14:19
 * @Description
 *      如果需原子地更新某个类里的某个字段时，就需要使用原子更新字段类， Atomic 包提供了以下 3 个类进行原子字段更新。
 *          ⚫ AtomicIntegerFieldUpdater：原子更新整型的字段的更新器。
 *          ⚫ AtomicLongFieldUpdater：原子更新长整型字段的更新器。
 *          ⚫ AtomicStampedReference：原子更新带有版本号的引用类型。该类将整数值与引
 *      用关联起来，可用于原子的更新数据和数据的版本号，可以解决使用 CAS 进行原子更新时可能出现的 ABA 问题。要想原子地更新字段类需要两步。
 *      第一步，因为原子更新字段类都是抽象类，每次使用的时候必须使用静态方法 newUpdater()创建一个更新器，并且需要设置想要更新的类和属性。
 *      第二步，更新类的字段（属性）必须使用 public volatile 修饰符。
 */
public class Demo21AtomicIntegerFieldUpdater {

    public static void main(String[] args) {
        // 创建原子更新器，并设置需要更新的对象类和对象的属性
        AtomicIntegerFieldUpdater<User> af = AtomicIntegerFieldUpdater.newUpdater(User.class,"age");

        User user = new User("张三",10);

        af.getAndSet(user,20);
        System.out.println(user);

        af.getAndIncrement(user);
        System.out.println(user);

    }

    private static class User {

        private String name;

        public volatile int age;

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
