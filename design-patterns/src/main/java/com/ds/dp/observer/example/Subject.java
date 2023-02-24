package com.ds.dp.observer.example;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author ds
 * @Date 2021/3/17 11:47
 * @Description
 */
public class Subject {

    private List<ObServer> list = new ArrayList<>();

    /**
     * 添加观察者
     * @param obServer
     */
    public void add(ObServer obServer){
        list.add(obServer);
    }

    /**
     * 删除观察者
     * @param obServer
     */
    public void del(ObServer obServer){
        list.remove(obServer);
    }

    /**
     * 有新报纸
     */
    public void notifyObServer(){

        list.forEach(obServer -> {
            obServer.update(this);
        });
    }

}
