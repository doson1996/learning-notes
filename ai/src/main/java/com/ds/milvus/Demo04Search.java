package com.ds.milvus;

import java.util.List;

import io.milvus.v2.client.MilvusClientV2;
import io.milvus.v2.service.vector.request.SearchReq;
import io.milvus.v2.service.vector.request.data.EmbeddedText;
import io.milvus.v2.service.vector.request.data.FloatVec;
import io.milvus.v2.service.vector.response.SearchResp;

/**
 * @author ds
 * @date 2026/7/10
 * @description
 */
public class Demo04Search {
    public static void main(String[] args) {
        MilvusClientV2 client = MilvusHelper.milvusClient();

        FloatVec queryVector = new FloatVec(new float[]{0.3580376395471989f, -0.6023495712049978f, 0.18414012509913835f, -0.26286205330961354f, 0.9029438446296592f});
        SearchReq searchReq = SearchReq.builder()
                .collectionName(MilvusHelper.COLLECTION_NAME)
                .data(List.of(queryVector))
                .annsField("vector")
                .outputFields(List.of("color"))
                .offset(0)
                .limit(3)
                .build();
        SearchResp search = client.search(searchReq);
        search.getSearchResults().forEach(System.out::println);
    }
}
