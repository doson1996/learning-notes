package com.ds;

import com.alibaba.fastjson2.JSONObject;
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
 * @date 2024/3/20
 * @description
 */
public class Demo01CreateCollection {
    public static void main(String[] args) {
        @Cleanup
        MongoClient client = new MongoClient("localhost", 27017);
        MongoDatabase ds = client.getDatabase("ds");

        // 创建 collection
        ds.createCollection("users");

        // 选择 collection
        MongoCollection<Document> users = ds.getCollection("users");

        // 新增单个文档
        JSONObject user = new JSONObject();
        user.put("name", "张三");
        user.put("age", "20");
        users.insertOne(Document.parse(user.toJSONString()));

        // 新增多个文档
        List<Document> persons = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            JSONObject person = new JSONObject();
            person.put("name", "person" + i);
            person.put("age", i);
            persons.add(Document.parse(person.toJSONString()));
        }
        users.insertMany(persons);

        System.out.println("新增数据--------------------");
        FindIterable<Document> userList = users.find();
        for (Document document : userList) {
            System.out.println("document = " + document);
        }


        // 更新 将文档中name = 张三 的文档修改为 name = 李四
        users.updateMany(Filters.eq("name", "张三"), new Document("$set", new Document("name", "李四")));
        System.out.println("更新数据--------------------");
        userList = users.find();
        for (Document document : userList) {
            System.out.println("document = " + document);
        }



        //删除符合条件的第一个文档
        users.deleteOne(Filters.eq("name", "张三"));

        //删除所有符合条件的文档
        users.deleteMany(Filters.eq("name", "person"));

        userList = users.find();
        for (Document document : userList) {
            System.out.println("document = " + document);
        }

        MongoCursor<Document> cursor = users.find().iterator();
        while (cursor.hasNext()) {
            Document doc = cursor.next();
            System.out.println("document = " + doc);
        }

    }
}
