package com.ds.dp.prototype.transform;

/**
 * @Author ds
 * @Date 2021/3/15 11:01
 * @Description 处理订单的业务对象，使用原型模式解决问题
 */
public class OrderBusiness {

    private static final int MAX_NUM = 1000;

    public void saveOrder(OrderApi order){

        int orderProductNum = order.getOrderProductNum();

        while (orderProductNum > MAX_NUM){

            OrderApi newOrder = order.clone();
            newOrder.setOrderProductNum(MAX_NUM);
            orderProductNum = orderProductNum - MAX_NUM;
            order.setOrderProductNum(orderProductNum);

            System.out.println("拆分订单" + newOrder);
        }

        System.out.println("订单" + order);
    }
}
