//package com.ds;
//
//import com.mongodb.BasicDBObject;
//import com.mongodb.MongoClient;
//import com.mongodb.client.ChangeStreamIterable;
//import com.mongodb.client.FindIterable;
//import com.mongodb.client.MongoCollection;
//import com.mongodb.client.MongoDatabase;
//import com.mongodb.client.model.changestream.ChangeStreamDocument;
//import com.mongodb.client.model.changestream.FullDocument;
//import lombok.Cleanup;
//import org.bson.Document;
//
///**
// * @author ds
// * @date 2024/3/20
// * @description 分页
// */
//public class Demo06ChangeStreams {
//    public static void main(String[] args) throws InterruptedException {
//        @Cleanup
//        MongoClient client = new MongoClient("localhost", 27017);
//        MongoDatabase ds = client.getDatabase("ds");
//
//        // 选择 collection
//        MongoCollection<Document> users = ds.getCollection("users");
//
//        while (true) {
//            ChangeStreamIterable<Document> iterable = users.watch().fullDocument(FullDocument.UPDATE_LOOKUP);
//
//            if (iterable.iterator().hasNext()) {
//                Thread.sleep(10000);
//            }
//
//            for (ChangeStreamDocument<Document> documentChangeStreamDocument : iterable) {
//                System.out.println("documentChangeStreamDocument = " + documentChangeStreamDocument.getFullDocument());
//            }
//        }
//    }
//}
