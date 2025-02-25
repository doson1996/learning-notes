package com.ds.dp.observer.simple;

/**
 * @Author ds
 * @Date 2021/3/17 11:24
 * @Description
 */
public class ConcreteSubject extends Subject {

    /**
     * 示意，目标对象的状态
     */
    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
        //状态发生改变，通知每个观察者
        this.notifyObServer();
    }
}
