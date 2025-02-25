package com.ds.dp.visitor.example;


import java.util.ArrayList;
import java.util.List;

/**
 * @Author ds
 * @Date 2021/4/14 14:01
 * @Description
 */
public class ObjectStructure {

    public List<Customer> list = new ArrayList<>();

    /**
     * 示意方法，提供给客户端操作的高层接口
     *
     * @param visitor
     */
    public void handlerRequest(Visitor visitor) {
        for (Customer customer : list) {
            customer.accept(visitor);
        }
    }

    /**
     * 示意方法，向对象结构中添加元素
     *
     * @param customer
     */
    public void addElement(Customer customer) {
        list.add(customer);
    }
}
