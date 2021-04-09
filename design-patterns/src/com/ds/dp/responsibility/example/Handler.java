package com.ds.dp.responsibility.example;

/**
 * @Author ds
 * @Date 2021/4/9 10:20
 * @Description 职责的接口，处理请求的接口
 */
public abstract class Handler {

    /**
     * 持有后继的职责对象
     */
    protected Handler handler;

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    /**
     * 处理聚餐费用的申请
     * @param user
     * @param fee
     * @return 申请结果通知
     */
    public abstract String handlerFeeRequest(String user, double fee);
}
