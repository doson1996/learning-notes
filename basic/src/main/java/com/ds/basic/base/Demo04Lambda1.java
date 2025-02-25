package com.ds.basic.base;

/**
 * @author ds
 * @date 2021/12/22 22:45
 */
public class Demo04Lambda1 {

    static class Person1 implements IPerson {
        @Override
        public void say() {
            System.out.println("静态内部类");
        }
    }

    public static void main(String[] args) {
        IPerson person = new Person();
        person.say();

        person = new Person1();
        person.say();

        class Person3 implements IPerson {
            @Override
            public void say() {
                System.out.println("局部内部类");
            }
        }

        person = new Person3();
        person.say();


        person = new IPerson() {
            @Override
            public void say() {
                System.out.println("匿名内部类");
            }
        };

        person.say();

        person = () -> {
            System.out.println("lambda");
        };

        person.say();
    }

}

interface IPerson {
    void say();
}

class Person implements IPerson {
    @Override
    public void say() {
        System.out.println(1);
    }
}