package com.ds.es;

import com.ds.es.bean.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;


/**
 * 局部更新文档
 * @author ds
 * @date 2022/3/9 22:47
 */
public class Demo03DocUpdate {
    public static void main(String[] args) throws Exception {
        //创建客户端对象
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http")));

        UpdateRequest request = new UpdateRequest();
        request.index("test").id("1001");
        request.doc(XContentType.JSON, "age", 25);

        UpdateResponse response = client.update(request, RequestOptions.DEFAULT);
        System.out.println(response.getResult());

        //关闭客户端
        client.close();
    }
}
