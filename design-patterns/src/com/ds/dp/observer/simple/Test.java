package com.ds.dp.observer.simple;

/**
 * @Author ds
 * @Date 2021/3/17 11:29
 * @Description
 */
public class Test {

    public static void main(String[] args) {

        ConcreteSubject concreteSubject = new ConcreteSubject();
        for (int i = 0; i < 10; i++) {
            concreteSubject.attach(new ConcreteObServer());
        }

        concreteSubject.setState("1");
    }
}
