package com.ds.dp.prototype;

/**
 * @Author ds
 * @Date 2021/3/15 10:30
 * @Description 原型模式, 未使用设计模式的解决方案
 * OrderBusiness 中依赖了订单的具体实现，不够灵活
 */
public class PrototypeClient {

    public static void main(String[] args) {

        EnterpriseOrder order = new EnterpriseOrder();
        order.setOrderProductNum(2304);
        order.setProductNo("pno.0101");
        order.setName("张三");

        OrderBusiness orderBusiness = new OrderBusiness();
        orderBusiness.saveOrder(order);
    }
}
