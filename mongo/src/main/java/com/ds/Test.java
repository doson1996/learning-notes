package com.ds;

import java.util.Arrays;

import com.mongodb.MongoClient;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 * @author ds
 * @date 2025/6/17
 * @description
 */
public class Test {
    public static void main(String[] args) {
        MongoClient client = new MongoClient("localhost", 27017);
        MongoDatabase database = client.getDatabase("ds");

        MongoCollection<Document> orders = database.getCollection("task");

        int pageNum = 1;
        int pageSize = 10;

        // 手动构建聚合管道（兼容 MongoDB Java Driver 3.5）
        AggregateIterable<Document> result = orders.aggregate(Arrays.asList(
                // $lookup 关联 users 表
                new Document("$lookup", new Document("from", "task_s")
                        .append("localField", "tn")
                        .append("foreignField", "tn")
                        .append("as", "tasks")),

                // 展开数组
                new Document("$unwind", "$tasks"),

                // 分页
                new Document("$skip", (pageNum - 1) * pageSize),
                new Document("$limit", pageSize)
        ));

        // 遍历结果（注意：Driver 3.x 返回 Object 类型）
        for (Object obj : result) {
            Document doc = (Document) obj;
            System.out.println(doc.toJson());
        }

        client.close();
    }
}
