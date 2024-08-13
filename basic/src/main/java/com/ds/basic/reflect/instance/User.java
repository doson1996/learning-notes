package com.ds.basic.reflect.instance;

import lombok.Data;

/**
 * @author ds
 */
@Data
public class User {

    static {
        System.out.println("user...");
    }

    User() {

    }

    private String name;

    public void say() {
        System.out.println("say...");
    }

}
