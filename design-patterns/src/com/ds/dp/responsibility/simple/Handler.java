package com.ds.dp.responsibility.simple;

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
     * 示意处理请求的方法
     */
    public abstract void handlerRequest();
}
