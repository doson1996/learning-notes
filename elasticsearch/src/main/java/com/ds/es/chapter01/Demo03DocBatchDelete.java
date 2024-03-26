package com.ds.es.chapter01;

import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;


/**
 * 批量删除文档
 * @author ds
 * @date 2022/3/9 22:47
 */
public class Demo03DocBatchDelete {
    public static void main(String[] args) throws Exception {
        //创建客户端对象
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http")));

        BulkRequest request = new BulkRequest();

        //批量删除数据
        request.add(new DeleteRequest().index("test").id("1001"));
        request.add(new DeleteRequest().index("test").id("1002"));
        request.add(new DeleteRequest().index("test").id("1003"));

        BulkResponse response = client.bulk(request, RequestOptions.DEFAULT);
        System.out.println(response.getTook());
        System.out.println(response.getItems());

        //关闭客户端
        client.close();
    }
}
