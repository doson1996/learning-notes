package com.ds.es;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;

/**
 * 查询文档 高级查询
 * @author ds
 * @date 2022/3/9 22:47
 */
public class Demo03DocQuery1 {
    public static void main(String[] args) throws Exception {
        //创建客户端对象
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http")));

        SearchRequest request = new SearchRequest();
        request.indices("test");

        //查询age = 20
      //  BoolQueryBuilder builder = QueryBuilders.boolQuery();
     //   builder.must(QueryBuilders.matchQuery("age", 20));

        // 范围查询
        RangeQueryBuilder builder = QueryBuilders.rangeQuery("age");
        // age >= 20
       // builder.gte("20");
        // age > 20
        builder.gt("20");

        SearchSourceBuilder query = new SearchSourceBuilder().query(builder);
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
