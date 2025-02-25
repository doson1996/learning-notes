package com.ds.dp.prototype;

/**
 * @Author ds
 * @Date 2021/3/15 11:01
 * @Description 处理订单的业务对象
 */
public class OrderBusiness {

    private static final int MAX_NUM = 1000;

    public void saveOrder(OrderApi order) {

        int orderProductNum = order.getOrderProductNum();

        while (orderProductNum > MAX_NUM) {

            OrderApi newOrder;

            if (order instanceof PersonOrder) {
                PersonOrder p1 = new PersonOrder();
                PersonOrder p2 = (PersonOrder) order;
                p1.setName(p2.getName());
                p1.setProductNo(p2.getProductNo());
                p1.setOrderProductNum(MAX_NUM);
                newOrder = p1;

            } else {
                EnterpriseOrder e1 = new EnterpriseOrder();
                EnterpriseOrder e2 = (EnterpriseOrder) order;
                e1.setName(e2.getName());
                e1.setProductNo(e2.getProductNo());
                e1.setOrderProductNum(MAX_NUM);
                newOrder = e1;
            }

            orderProductNum = orderProductNum - MAX_NUM;
            order.setOrderProductNum(orderProductNum);

            System.out.println("拆分订单" + newOrder);
        }

        System.out.println("订单" + order);
    }
}
