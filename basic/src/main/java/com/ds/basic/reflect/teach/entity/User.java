package com.ds.basic.reflect.teach.entity;

/**
 * @author ds
 * @date 2024/11/13 21:02
 */
public class User {

    private String name;

    public User() {
    }

    public User (String name) {
        this.name = name;
    }

    public void sayHello() {
        System.out.println("hello " + name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}
