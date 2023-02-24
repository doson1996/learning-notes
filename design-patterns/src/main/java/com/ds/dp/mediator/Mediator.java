package com.ds.dp.mediator;

/**
 * @Author ds
 * @Date 2021/3/15 14:41
 * @Description 中介者，定义各个同事对象通信的接口
 */
public interface Mediator {

    /**
     * 同事对象在改变自身时通知中介者，让其去负责相应的与其他同事对象的交互
     * @param colleague 同事对象自身
     */
    void change(Colleague colleague);
}
