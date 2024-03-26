package com.ds.es.chapter01;

import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

/**
 * 查询文档
 * @author ds
 * @date 2022/3/9 22:47
 */
public class Demo03DocSearch {
    public static void main(String[] args) throws Exception {
        //创建客户端对象
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http")));

        //查询数据
        GetRequest request = new GetRequest();
        request.index("test").id("1001");

        GetResponse response = client.get(request, RequestOptions.DEFAULT);
        System.out.println(response.getSourceAsString());
        System.out.println(response.getSource());
        //关闭客户端
        client.close();
    }
}
