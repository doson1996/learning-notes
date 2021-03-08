package com.ds.dp.adapter;

/**
 * @Author ds
 * @Date 2021/3/5 14:36
 * @Version 1.0
 * @Description 日志数据对象
 */
public class LogModel {

    /**
     * id
     */
    private String id;

    /**
     * 内容
     */
    private String content;

    public LogModel() {
    }

    public LogModel(String id, String content) {
        this.id = id;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "LogModel{" +
                "id='" + id + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
