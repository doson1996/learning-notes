package com.ds.es;

import com.ds.es.bean.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;


/**
 * 批量创建文档
 * @author ds
 * @date 2022/3/9 22:47
 */
public class Demo03DocBatchCreate {
    public static void main(String[] args) throws Exception {
        //创建客户端对象
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http")));

        BulkRequest request = new BulkRequest();
        ObjectMapper mapper = new ObjectMapper();
        User user = new User();
        user.setName("张三");
        user.setAge(20);
        user.setSex("男");
        String user1 = mapper.writeValueAsString(user);
        user.setName("李四");
        String user2 = mapper.writeValueAsString(user);
        user.setName("王五");
        String user3 = mapper.writeValueAsString(user);

        //批量插入数据
        request.add(new IndexRequest().index("test").id("1001").source(user1, XContentType.JSON));
        request.add(new IndexRequest().index("test").id("1002").source(user2, XContentType.JSON));
        request.add(new IndexRequest().index("test").id("1003").source(user3, XContentType.JSON));

        BulkResponse response = client.bulk(request, RequestOptions.DEFAULT);
        System.out.println(response.getTook());
        System.out.println(response.getItems());

        //关闭客户端
        client.close();
    }
}
