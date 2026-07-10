package com.ds.milvus;

import io.milvus.v2.client.ConnectConfig;
import io.milvus.v2.client.MilvusClientV2;
import io.milvus.v2.common.DataType;
import io.milvus.v2.service.collection.request.AddFieldReq;
import io.milvus.v2.service.collection.request.CreateCollectionReq;
import io.milvus.v2.service.collection.request.DropCollectionReq;

/**
 * @author ds
 * @date 2026/7/10
 * @description
 */
public class Demo01Collection {

    public static void main(String[] args) {
        MilvusClientV2 client = MilvusHelper.milvusClient();

        // 3.1 Create schema
        CreateCollectionReq.CollectionSchema schema = client.createSchema();

        // 3.2 Add fields to schema
        schema.addField(AddFieldReq.builder()
                .fieldName("id")
                .dataType(DataType.Int64)
                .isPrimaryKey(true)
                .autoID(false)
                .build());

        schema.addField(AddFieldReq.builder()
                .fieldName("vector")
                .dataType(DataType.FloatVector)
                .dimension(5)
                .build());

        schema.addField(AddFieldReq.builder()
                .fieldName("color")
                .dataType(DataType.VarChar)
                .maxLength(32)
                .build());

        CreateCollectionReq customizedSetupReq2 = CreateCollectionReq.builder()
                .collectionName(MilvusHelper.COLLECTION_NAME)
                .collectionSchema(schema)
                .build();

        // 先删除
        DropCollectionReq dropDsParam = DropCollectionReq.builder()
                .collectionName(MilvusHelper.COLLECTION_NAME)
                .build();
        client.dropCollection(dropDsParam);

        // 再创建
        client.createCollection(customizedSetupReq2);
    }

}
