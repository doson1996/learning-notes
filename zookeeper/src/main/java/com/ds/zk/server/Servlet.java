package com.ds.zk.server;

/**
 * @author ds
 */
public abstract class Servlet {

    // 类似于HttpSerlvet
    public void service(Request request, Response response) {
        if ("POST".equalsIgnoreCase(request.getMethod())) {
            doPost(request, response);
        } else if ("GET".equalsIgnoreCase(request.getMethod())) {
            doGet(request, response);
        }
    }

    // 分别处理POST和GET请求
    public abstract void doPost(Request request, Response response);

    public abstract void doGet(Request request, Response response);

}
