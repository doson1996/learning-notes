package com.ds.es.chapter02;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.hutool.json.JSONUtil;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

/**
 * @author ds
 * @date 2026/9/17
 * @description
 */
public class Demo02DocBatchCreate {

    public static void main(String[] args) throws IOException {
        RestHighLevelClient restHighLevelClient = ESClient.getRestHighLevelClient();
        // 批量查询酒店数据
        List<Hotel> hotels = hotels();

        // 1.创建Request
        BulkRequest request = new BulkRequest();
        // 2.准备参数，添加多个新增的Request
        for (Hotel hotel : hotels) {
            // 2.1.转换为文档类型HotelDoc
            HotelDoc hotelDoc = new HotelDoc(hotel);
            // 2.2.创建新增文档的Request对象
            request.add(new IndexRequest("hotel")
                    .id(hotelDoc.getId().toString())
                    .source(JSONUtil.toJsonStr(hotelDoc), XContentType.JSON));
        }
        // 3.发送请求       //todo 有报错
        restHighLevelClient.bulk(request, RequestOptions.DEFAULT);

        restHighLevelClient.close();
    }

    private static List<Hotel> hotels() {
        List<Hotel> result = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            Hotel hotel = new Hotel();
            hotel.setId((long) i);
            hotel.setName("酒店" + i);
            hotel.setAddress("地址" + i);
            hotel.setCity("城市" + i);
            hotel.setBrand("品牌" + i);
            hotel.setLatitude("1");
            hotel.setLongitude("1");
            result.add(hotel);
        }

        return result;
    }

}
