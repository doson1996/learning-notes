package com.ds.springframework.chapter01.xhyl;

import java.util.ArrayList;

/**
 * @author ds
 */
public class User {

    public void sayHello() {
        System.out.println("hello");
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
