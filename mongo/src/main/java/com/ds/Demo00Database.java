package com.ds;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.CreateCollectionOptions;

/**
 * @author ds
 * @date 2024/3/25 23:37
 */
public class Demo00Database {
    public static void main(String[] args) {
        MongoClient mongoClient = new MongoClient();

        // 选择数据库
        MongoDatabase nasRc = mongoClient.getDatabase("d-nas-rc");

        // 创建集合
        CreateCollectionOptions createCollectionOptions = new CreateCollectionOptions()
                .capped(true)                   //固定大小集合
                .sizeInBytes(20 * 1024 * 1024)  //最大20MB
                .maxDocuments(20);              //最大文档数 20

        // 遍历集合
        for (String collectionName : nasRc.listCollectionNames()) {
            System.out.println("collectionName = " + collectionName);
        }

        // 创建一个日志表
        nasRc.createCollection("op_log", createCollectionOptions);

        // 遍历数据库
        MongoIterable<String> dbs = mongoClient.listDatabaseNames();
        for (String db : dbs) {
            System.out.println("db = " + db);
        }

        mongoClient.close();
    }
}
