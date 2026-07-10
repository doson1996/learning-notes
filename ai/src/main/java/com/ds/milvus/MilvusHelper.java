package com.ds.milvus;

import io.milvus.v2.client.ConnectConfig;
import io.milvus.v2.client.MilvusClientV2;

/**
 * @author ds
 * @date 2026/7/10
 * @description
 */
public class MilvusHelper {

    public static final String COLLECTION_NAME = "ds";

    private static final String CLUSTER_ENDPOINT = "http://ds.com:19530";

    private static final String TOKEN = "root:Milvus";

    public static MilvusClientV2 milvusClient() {
        // 1. Connect to Milvus server
        ConnectConfig connectConfig = ConnectConfig.builder()
                .uri(CLUSTER_ENDPOINT)
//                .token(TOKEN)
                .build();

        return new MilvusClientV2(connectConfig);
    }

}
