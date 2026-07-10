package com.ds.milvus;

import io.milvus.v2.client.ConnectConfig;
import io.milvus.v2.client.MilvusClientV2;
import io.milvus.v2.common.DataType;
import io.milvus.v2.service.collection.request.AddFieldReq;
import io.milvus.v2.service.collection.request.CreateCollectionReq;

/**
 * @author ds
 * @date 2026/7/10
 * @description
 */
public class CollectionDemo {

    public static void main(String[] args) {
        String CLUSTER_ENDPOINT = "http://ds.com:19530";
        String TOKEN = "root:Milvus";

        // 1. Connect to Milvus server
        ConnectConfig connectConfig = ConnectConfig.builder()
                .uri(CLUSTER_ENDPOINT)
//                .token(TOKEN)
                .build();

        MilvusClientV2 client = new MilvusClientV2(connectConfig);

        // 3. Create a collection in customized setup mode

        // 3.1 Create schema
        CreateCollectionReq.CollectionSchema schema = client.createSchema();

        // 3.2 Add fields to schema
        schema.addField(AddFieldReq.builder()
                .fieldName("my_id")
                .dataType(DataType.Int64)
                .isPrimaryKey(true)
                .autoID(false)
                .build());

        schema.addField(AddFieldReq.builder()
                .fieldName("my_vector")
                .dataType(DataType.FloatVector)
                .dimension(5)
                .build());

        schema.addField(AddFieldReq.builder()
                .fieldName("my_varchar")
                .dataType(DataType.VarChar)
                .maxLength(512)
                .build());


        CreateCollectionReq customizedSetupReq2 = CreateCollectionReq.builder()
                .collectionName("ds")
                .collectionSchema(schema)
                .build();

        client.createCollection(customizedSetupReq2);
    }

}
