package com.ds.dp.prototype.transform;

/**
 * @Author ds
 * @Date 2021/3/15 10:39
 * @Description 个人订单
 */
public class PersonOrder implements OrderApi {

    /**
     * 订购人姓名
     */
    private String name;

    /**
     * 产品编号
     */
    private String productNo;

    /**
     * 订购数量
     */
    private int num;

    @Override
    public int getOrderProductNum() {
        return num;
    }

    @Override
    public void setOrderProductNum(int num) {
        this.num = num;
    }

    @Override
    public OrderApi clone() {
        //创建一个订单，然后把本实例数据复制过去
        PersonOrder order = new PersonOrder();
        order.setName(this.name);
        order.setOrderProductNum(this.getOrderProductNum());
        order.setProductNo(this.productNo);

        return order;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    @Override
    public String toString() {
        return "个人订单订购人:" + name
                + "，产品编号:" + productNo
                + "，数量:" + num;
    }

}
