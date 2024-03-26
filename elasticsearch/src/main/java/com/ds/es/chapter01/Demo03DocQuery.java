package com.ds.es.chapter01;

import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;

/**
 * 查询文档 高级查询
 * @author ds
 * @date 2022/3/9 22:47
 */
public class Demo03DocQuery {
    public static void main(String[] args) throws Exception {
        //创建客户端对象
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http")));

        SearchRequest request = new SearchRequest();
        request.indices("test");

        QueryBuilder queryBuilder;
        //查询索引中的全部数据
        queryBuilder = QueryBuilders.matchAllQuery();

        // 查询 age = 20 的数据
        // queryBuilder = QueryBuilders.termQuery("age", "20");

        SearchSourceBuilder query = new SearchSourceBuilder().query(queryBuilder);

        //分页查询
       // query.from(2);    //(当前页码 - 1) * 每页显示数据
       // query.size(2);

        // 排序
        query.sort("age", SortOrder.DESC);

        // 显示指定字段
        String[] includes = {"name", "age"};   //包含
        String[] excludes = {"name"};         //排除
        query.fetchSource(includes, excludes);

        request.source(query);

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
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
