package com.ds.basic.oop;

/**
 * @Author ds
 * @Date 2021/3/21 14:00
 * @Description
 */
public class Person {

    private String name;

    private int age;

    public Person(){

    }

    public Person(String name,int age){
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
}
