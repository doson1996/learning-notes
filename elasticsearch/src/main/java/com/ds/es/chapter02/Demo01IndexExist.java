package com.ds.es.chapter02;

import java.io.IOException;

import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;

/**
 * @author ds
 * @date 2026/9/17
 * @description
 */
public class Demo01IndexExist {

    public static void main(String[] args) throws IOException {
        RestHighLevelClient restHighLevelClient = ESClient.getRestHighLevelClient();
        // 1.创建Request对象
        GetIndexRequest request = new GetIndexRequest("hotel");
        // 2.发送请求
        boolean exists = restHighLevelClient.indices().exists(request, RequestOptions.DEFAULT);
        // 3.输出
        System.out.println(exists ? "索引库已经存在！" : "索引库不存在！");

        restHighLevelClient.close();
    }

}
