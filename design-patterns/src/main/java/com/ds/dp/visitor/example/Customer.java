package com.ds.dp.visitor.example;

/**
 * @Author ds
 * @Date 2021/4/14 11:35
 * @Description 各种客户的父类
 */
public abstract class Customer {

    private String id;

    private String name;

    /**
     * 接受访问者访问
     *
     * @param visitor
     */
    public abstract void accept(Visitor visitor);

    /**
     * 客户提出的请求方法
     */
    protected abstract void serviceRequest();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
