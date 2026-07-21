package com.ds.milvus;

import java.util.Arrays;
import java.util.List;

import io.milvus.v2.client.MilvusClientV2;
import io.milvus.v2.service.collection.request.LoadCollectionReq;
import io.milvus.v2.service.vector.request.GetReq;
import io.milvus.v2.service.vector.request.QueryReq;
import io.milvus.v2.service.vector.request.SearchReq;
import io.milvus.v2.service.vector.response.GetResp;
import io.milvus.v2.service.vector.response.QueryResp;

/**
 * @author ds
 * @date 2026/7/10
 * @description
 */
public class Demo03Query {
    public static void main(String[] args) {
        MilvusClientV2 client = MilvusHelper.milvusClient();

        client.loadCollection(LoadCollectionReq.builder()
                .collectionName(MilvusHelper.COLLECTION_NAME)  // 使用名称，而非 ID
                .build());

        GetReq getReq = GetReq.builder()
                .collectionName(MilvusHelper.COLLECTION_NAME)
                .ids(Arrays.asList(0, 1, 2))
                .outputFields(Arrays.asList("vector", "color"))
                .build();

        GetResp getResp = client.get(getReq);

        List<QueryResp.QueryResult> results = getResp.getGetResults();
        for (QueryResp.QueryResult result : results) {
            System.out.println(result.getEntity());
        }

    }
}
