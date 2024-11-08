package com.ds.es.chapter01;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;

/**
 * 查询文档 高级查询
 *
 * @author ds
 * @date 2022/3/9 22:47
 */
public class Demo03DocQuery3 {
    public static void main(String[] args) throws Exception {
        //创建客户端对象
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http")));

        SearchRequest request = new SearchRequest();
        request.indices("test");

        // 聚合查询
        // age 最大
       // MaxAggregationBuilder builder = AggregationBuilders.max("maxAge").field("age");

        // 按 age 分组
        TermsAggregationBuilder builder = AggregationBuilders.terms("age_groupBy").field("age");

        SearchSourceBuilder query = new SearchSourceBuilder();
        query.aggregation(builder);
        request.source(query);

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        System.out.println(response);
        System.out.println("总数据条数: " + response.getHits().getTotalHits());
        System.out.println("响应时间: " + response.getTook());
        //数据
        SearchHits hits = response.getHits();
        for (SearchHit hit : hits) {
            System.out.println(hit.getSourceAsString());
        }

        //关闭客户端
        client.close();
    }
}
