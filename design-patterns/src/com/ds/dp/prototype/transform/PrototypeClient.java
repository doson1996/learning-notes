package com.ds.dp.prototype.transform;

/**
 * @Author ds
 * @Date 2021/3/15 10:30
 * @Description 原型模式
 */
public class PrototypeClient {

    public static void main(String[] args) {

        PersonOrder order = new PersonOrder();
        order.setOrderProductNum(3304);
        order.setProductNo("pno.0102");
        order.setName("李四");

        OrderBusiness orderBusiness = new OrderBusiness();
        orderBusiness.saveOrder(order);
    }
}
