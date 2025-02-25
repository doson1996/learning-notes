package com.ds.dp.observer.simple;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author ds
 * @Date 2021/3/17 11:15
 * @Description 目标对象，提供注册和删除观察者
 */
public class Subject {

    /**
     * 观察者对象列表
     */
    private List<ObServer> list = new ArrayList<>();

    /**
     * 注册
     *
     * @param obServer
     */
    public void attach(ObServer obServer) {
        list.add(obServer);
    }

    /**
     * 删除
     */
    public void detach(ObServer obServer) {
        list.remove(obServer);
    }

    /**
     * 通知
     */
    public void notifyObServer() {
        for (ObServer obServer : list) {
            obServer.update(this);
        }
    }
}
