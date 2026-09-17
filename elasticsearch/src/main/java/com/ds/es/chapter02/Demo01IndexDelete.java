package com.ds.es.chapter02;

import java.io.IOException;

import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;

/**
 * @author ds
 * @date 2026/9/17
 * @description
 */
public class Demo01IndexDelete {

    public static void main(String[] args) throws IOException {
        RestHighLevelClient restHighLevelClient = ESClient.getRestHighLevelClient();
        // 1.创建Request对象
        DeleteIndexRequest request = new DeleteIndexRequest("hotel");
        // 2.发送请求
        restHighLevelClient.indices().delete(request, RequestOptions.DEFAULT);

        restHighLevelClient.close();
    }

}
