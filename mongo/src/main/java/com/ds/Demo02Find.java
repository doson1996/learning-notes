package com.ds;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import lombok.Cleanup;
import org.bson.Document;

/**
 * @author ds
 * @date 2024/3/20
 * @description
 */
public class Demo02Find {
    public static void main(String[] args) {
        @Cleanup
        MongoClient client = new MongoClient("localhost", 27017);
        MongoDatabase ds = client.getDatabase("ds");

        // 选择 collection
        MongoCollection<Document> users = ds.getCollection("users");

        FindIterable<Document> documents = users.find(Filters.eq("name", "person1"));
        for (Document document : documents) {
            System.out.println("document = " + document);
        }
    }
}
