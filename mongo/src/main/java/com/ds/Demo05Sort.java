package com.ds;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import lombok.Cleanup;
import org.bson.Document;

/**
 * @author ds
 * @date 2024/3/20
 * @description 分页
 */
public class Demo05Sort {
    public static void main(String[] args) {
        @Cleanup
        MongoClient client = new MongoClient("localhost", 27017);
        MongoDatabase ds = client.getDatabase("ds");

        // 选择 collection
        MongoCollection<Document> users = ds.getCollection("users");

       // FindIterable<Document> documents = users.find().sort(Sorts.descending("age"));

        // 按年龄升序（降序 -1）
        BasicDBObject sort = new BasicDBObject();
        sort.put("age", 1);
        FindIterable<Document> documents = users.find().sort(sort);

        for (Document document : documents) {
            System.out.println("document = " + document);
        }
    }
}
