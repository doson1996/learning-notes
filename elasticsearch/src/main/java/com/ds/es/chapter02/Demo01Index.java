package com.ds.es.chapter02;

import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.rest.RestRequest;

/**
 * @author ds
 * @date 2024/3/26
 * @description
 */
public class Demo01Index {
    public static void main(String[] args) throws Exception {
        RestClient client = ESClient.getClient();
        // 创建索引
        Request request = new Request(RestRequest.Method.PUT.toString(), "idx_user");

        // 删除索引
        // Request request = new Request(RestRequest.Method.DELETE.toString(), "idx_user");

        Response response = client.performRequest(request);
        System.out.println("response = " + response);

        client.close();
    }
}
