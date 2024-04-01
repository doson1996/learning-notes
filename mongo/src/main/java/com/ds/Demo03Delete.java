package com.ds;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import lombok.Cleanup;
import org.bson.Document;

import java.util.regex.Pattern;

/**
 * @author ds
 * @date 2024/4/1
 * @description 删除
 */
public class Demo03Delete {
    public static void main(String[] args) {
        @Cleanup
        MongoClient client = new MongoClient("localhost", 27017);
        MongoDatabase ds = client.getDatabase("ds");

        MongoCollection<Document> collection = ds.getCollection("users");
        DeleteResult deleteResult = collection.deleteOne(Filters.eq("name", "lisi"));
        System.out.println("deleteResult = " + deleteResult);

        // 删除 like %person%
        deleteResult = collection.deleteMany(Filters.regex("name", "person"));
        System.out.println("deleteResult = " + deleteResult);

        // 删除 like %李%
        Pattern pattern = Pattern.compile(".*李.*");
        deleteResult = collection.deleteMany(Filters.eq("name", pattern));
        System.out.println("deleteResult = " + deleteResult);

        // 删除 like %四
        pattern = Pattern.compile(".*四");
        deleteResult = collection.deleteMany(Filters.eq("name", pattern));
        System.out.println("deleteResult = " + deleteResult);
    }
}
