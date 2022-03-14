package com.ds.es;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;

import java.util.Arrays;

/**
 * 索引查询
 * @author ds
 * @date 2022/3/9 22:47
 */
public class Demo02IndexSerach {
    public static void main(String[] args) throws Exception {
        //创建客户端对象
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http")));

        GetIndexRequest getIndexRequest = new GetIndexRequest("test");
        GetIndexResponse getIndexResponse = client.indices().get(getIndexRequest, RequestOptions.DEFAULT);
        System.out.println(Arrays.toString(getIndexResponse.getIndices()));
        System.out.println(getIndexResponse.getAliases());
        System.out.println(getIndexResponse.getSettings());
        System.out.println(getIndexResponse.getMappings());
        //关闭客户端
        client.close();
    }
}
