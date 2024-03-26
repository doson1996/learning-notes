package com.ds.es.chapter01;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;


/**
 * 索引删除
 * @author ds
 * @date 2022/3/9 22:47
 */
public class Demo02IndexDelete {
    public static void main(String[] args) throws Exception {
        //创建客户端对象
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http")));

        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest("test");
        AcknowledgedResponse delete = client.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
        System.out.println("删除索引：" + delete.isAcknowledged());

        //关闭客户端
        client.close();
    }
}
