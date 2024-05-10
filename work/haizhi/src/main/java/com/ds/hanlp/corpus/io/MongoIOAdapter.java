package com.ds.hanlp.corpus.io;

import com.ds.db.mongo.MongoDB;
import com.hankcs.hanlp.corpus.io.FileIOAdapter;
import org.bson.Document;

import java.io.*;
import java.nio.file.Files;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author ds
 * @date 2024/5/10
 * @description
 */
public class MongoIOAdapter extends FileIOAdapter {

    /**
     * 初始化标识
     */
    private static final AtomicBoolean NEED_INIT = new AtomicBoolean(true);

    @Override
    public InputStream open(String path) throws FileNotFoundException {
        if (NEED_INIT.get()) {
            try {
                System.out.println("读取mongo配置数据");
                List<Document> list = MongoDB.query();

                File file = new File( "/Users/ds/IdeaProjects/config/data/dictionary/custom/mongo.txt");
                Writer writer = new OutputStreamWriter(Files.newOutputStream(file.toPath()));

                for (int i = 0; i < list.size(); i++) {
                    Document document = list.get(i);
                    String question = document.getString("question");
                    String line = question + " hzq 1";
                    if (i < list.size() - 1) {
                        line = line + "\r\n";
                    }
                    System.out.print("读到配置：" + line);
                    writer.write(line);
                }

                writer.close();
            } catch (Exception e) {
                System.out.println("发生异常: " + e);
            }
            NEED_INIT.set(false);
        }
        return super.open(path);
    }

    @Override
    public OutputStream create(String path) throws FileNotFoundException {
        return super.create(path);
    }

}
