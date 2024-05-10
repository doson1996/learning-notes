package com.ds.db.mongo;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import lombok.Cleanup;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ds
 * @date 2024/5/10
 * @description
 */
public class MongoDB {

    public static List<Document> query() {
        @Cleanup
        MongoClient client = new MongoClient("localhost", 27017);
        MongoDatabase ds = client.getDatabase("ds");

        // 选择 collection
        MongoCollection<Document> question = ds.getCollection("nl2sql_question");

        List<Document> result = new ArrayList<>();
        FindIterable<Document> documents = question.find();
        for (Document document : documents) {
            result.add(document);
        }

        return result;
    }

}
