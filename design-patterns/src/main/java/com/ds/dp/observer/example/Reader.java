package com.ds.dp.observer.example;

/**
 * @Author ds
 * @Date 2021/3/17 11:58
 * @Description
 */
public class Reader implements ObServer {

    private String name;

    @Override
    public void update(Subject subject) {
        Newspaper newspaper = (Newspaper) subject;
        System.out.println(name + "--读到新报纸--" + newspaper.getContent());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
