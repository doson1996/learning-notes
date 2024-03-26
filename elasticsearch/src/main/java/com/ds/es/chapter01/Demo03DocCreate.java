package com.ds.es.chapter01;

import com.ds.es.chapter01.bean.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.util.Random;


/**
 * 创建文档
 * 全量更新
 * @author ds
 * @date 2022/3/9 22:47
 */
public class Demo03DocCreate {
    public static void main(String[] args) throws Exception {
        //创建客户端对象
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http")));

        IndexRequest request = new IndexRequest();
        request.index("test").id("1001");
        //向es里存放数据  必须将数据转换为json格式
        User user = new User();
        user.setName("张三");
        user.setAge(new Random().nextInt());
        user.setSex("男");
        ObjectMapper mapper = new ObjectMapper();
        String userJson = mapper.writeValueAsString(user);
        request.source(userJson, XContentType.JSON);

        IndexResponse response = client.index(request, RequestOptions.DEFAULT);
        System.out.println(response.getResult());

        //关闭客户端
        client.close();
    }
}
