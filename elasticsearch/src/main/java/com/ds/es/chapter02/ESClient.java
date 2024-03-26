package com.ds.es.chapter02;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

/**
 * @author ds
 * @date 2024/3/26
 * @description es客户端
 */
public class ESClient {

    public static RestHighLevelClient getRestHighLevelClient() {
        //创建客户端对象
        return getRestHighLevelClient("localhost", 9200, "http");
    }

    public static RestHighLevelClient getRestHighLevelClient(String host, int port, String scheme) {
        //创建客户端对象
        return new RestHighLevelClient(RestClient.builder(new HttpHost(host, port, scheme)));
    }

    public static RestClient getClient() {
        //创建客户端对象
        return getClient("localhost", 9200, "http");
    }

    public static RestClient getClient(String host, int port, String scheme) {
        //创建客户端对象
        return RestClient.builder(new HttpHost(host, port, scheme)).build();
    }

}
