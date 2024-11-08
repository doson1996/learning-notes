package com.ds.es.chapter01;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.xcontent.XContentType;

/**
 * 创建文档
 * 全量更新
 *
 * @author ds
 * @date 2022/3/9 22:47
 */
public class CrcpCompanyDocCreate {

    public static void main(String[] args) throws Exception {
        //创建客户端对象
        RestHighLevelClient client = getClient();

        // 创建索引1
//        CreateIndexRequest createIndexRequest = new CreateIndexRequest("enterprise_data_gov");
//        CreateIndexResponse createIndexResponse = client.indices().create(createIndexRequest, RequestOptions.DEFAULT);

        IndexRequest request = new IndexRequest();

        //向es里存放数据  必须将数据转换为json格式

//        request.index("enterprise_data_gov").id("重庆测试公司915000002024110501");
        String json = "{\n" +
                "\t\"eid\": \"重庆测试公司915000002024110501\",\n" +
                "\t\"zczj\": \"2000万人民币元\",\n" +
                "\t\"dh\": \"暂无信息\",\n" +
                "\t\"dz\": \"重庆市江北区永平门xxx号\",\n" +
                "\t\"clrq\": \"1996-09-02\",\n" +
                "\t\"name\": \"重庆测试公司\",\n" +
                "\t\"fddbr\": \"张三\",\n" +
                "\t\"sjkzr\": \"李四\",\n" +
                "\t\"zt\": \"在营\"\n" +
                "}";
//        JSONObject jsonObject = JSONUtil.parseObj(json);
//        String jsonString = jsonObject.toJSONString(2);

        request.index("enterprise_data_gov").id("重庆测试技术有限公司915000002024110502");
        json = "{\n" +
                "\t\"eid\": \"重庆测试技术有限公司915000002024110502\",\n" +
                "\t\"zczj\": \"12000.00万人民币元\",\n" +
                "\t\"dh\": \"13012345678\",\n" +
                "\t\"dz\": \"重庆市江北区永平门xxx号\",\n" +
                "\t\"clrq\": \"2002-07-02\",\n" +
                "\t\"name\": \"重庆测试技术有限公司\",\n" +
                "\t\"fddbr\": \"张三\",\n" +
                "\t\"sjkzr\": \"李四\",\n" +
                "\t\"zt\": \"在营\"\n" +
                "}";

        request.source(json, XContentType.JSON);

        IndexResponse response = client.index(request, RequestOptions.DEFAULT);
        System.out.println(response.getResult());

        //关闭客户端
        client.close();
    }

    private static RestHighLevelClient getClient() {
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(
                AuthScope.ANY, new UsernamePasswordCredentials("elastic", "Haizhi@2022")
        );

        RestClientBuilder builder = RestClient.builder(new HttpHost("192.168.1.238", 9200))
                .setHttpClientConfigCallback(httpClientBuilder -> httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider));
        return new RestHighLevelClient(builder);
    }

}
