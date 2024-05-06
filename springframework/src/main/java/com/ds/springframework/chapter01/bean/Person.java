package com.ds.springframework.chapter01.bean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 在自定义对象里设置ApplicationContext
 * @author ds
 */
public class Person implements ApplicationContextAware {

    private Integer id;

    private String name;

    private ApplicationContext applicationContext;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void say() {
        System.out.println(Thread.currentThread().getName() + "  say...");
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", applicationContext=" + applicationContext +
                '}';
    }

}
