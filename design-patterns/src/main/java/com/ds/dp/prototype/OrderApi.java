package com.ds.dp.prototype;

/**
 * @Author ds
 * @Date 2021/3/15 10:36
 * @Description 订单的接口
 */
public interface OrderApi {

    /**
     * 获取订单产品的数量
     *
     * @return
     */
    int getOrderProductNum();

    /**
     * 设置订单产品的数量
     *
     * @param num
     */
    void setOrderProductNum(int num);
}
