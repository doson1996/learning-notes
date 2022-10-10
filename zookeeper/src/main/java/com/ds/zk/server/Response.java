package com.ds.zk.server;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author ds
 */
public class Response {

    private OutputStream out;

    public Response(OutputStream out) {
        super();
        this.out = out;
    }

    /**
     * 该方法前面的步骤都是为了写好响应头，最后一句话才是写入响应内容
     *
     * @param content
     * @param statusCode
     * @throws IOException
     */
    public void write(String content, int statusCode) throws IOException {
        out.write(("HTTP/1.1 " + statusCode + " OK\n").getBytes());
        out.write("Content-Type:text/html;Charset=utf-8\n".getBytes());
        out.write("\n".getBytes());
        // 这里处理编码问题
        out.write(content.getBytes("UTF-8"));
    }
}
