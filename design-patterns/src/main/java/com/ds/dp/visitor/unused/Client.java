package com.ds.dp.visitor.unused;

/**
 * @Author ds
 * @Date 2021/4/14 11:47
 * @Description 不使用设计模式
 */
public class Client {

    public static void main(String[] args) {
        Customer pCustomer = new PersonCustomer();
        pCustomer.setName("张三");
        pCustomer.serviceRequest();

        Customer eCustomer = new EnterpriseCustomer();
        eCustomer.setName("xx");
        eCustomer.serviceRequest();
    }
}
