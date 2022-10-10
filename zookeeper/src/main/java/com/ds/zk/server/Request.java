package com.ds.zk.server;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author ds
 */
public class Request {

    // 请求地址
    private String url;

    // 请求方法
    private String method;

    // 请求参数
    private String params;

    // 构造函数，参数为后面2.4中Socket建立的IO流
    public Request(InputStream in) throws IOException {

        // IO读取请求
        // 这里踩坑、因为http/1.1是长连接，所以浏览器未超时是不会主动关闭的
        // 不能使用循环来读取数据，因为读取不了-1(未主动关闭)
        byte[] bytes = new byte[1024];
        int length = in.read(bytes);
        String str = new String(bytes, 0, length);

        // 取请求的第一行（具体请求信息请看序文中的HTTP知识铺垫）
        String strFirst = str.split("\n")[0];
        // 按空格分割
        String[] arr = strFirst.split(" ");

        // 从第一行中获取方法名和请求地址
        method = arr[0];
        url = arr[1];
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }
}
