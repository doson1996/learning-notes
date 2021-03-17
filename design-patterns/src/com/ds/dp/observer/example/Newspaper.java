package com.ds.dp.observer.example;

/**
 * @Author ds
 * @Date 2021/3/17 11:56
 * @Description 报纸
 */
public class Newspaper extends Subject{

    /**
     * 报纸的内容
     */
    private String content;

    public String getContent() {
        return content;
    }

    /**
     * 新的报纸
     * @param content
     */
    public void setContent(String content) {
        //一定要先改变内容，再发出通知。不然很容易发生观察者和目标对象内容不一致
        this.content = content;
        this.notifyObServer();
    }
}
