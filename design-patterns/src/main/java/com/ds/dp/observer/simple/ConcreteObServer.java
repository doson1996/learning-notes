package com.ds.dp.observer.simple;

/**
 * @Author ds
 * @Date 2021/3/17 11:25
 * @Description
 */
public class ConcreteObServer implements ObServer {

    /**
     * 观察者的状态
     */
    private String obServerState;

    @Override
    public void update(Subject subject) {
        ConcreteSubject concreteSubject = (ConcreteSubject) subject;
        this.obServerState = concreteSubject.getState();
        System.out.println("观察到状态改变---" + obServerState);
    }
}
