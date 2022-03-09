package com.ds.es;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.transport.TransportClient;

import java.io.IOException;

/**
 * @author ds
 * @date 2022/3/9 22:47
 */
public class Demo01Client {
    public static void main(String[] args) throws Exception {
        //创建客户端对象
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http")));

        System.out.println("client = " + client);
        //关闭客户端
        client.close();
    }
}
