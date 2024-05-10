package com.ds.hanlp.corpus.io;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.ds.db.mongo.MongoDB;
import com.hankcs.hanlp.corpus.io.FileIOAdapter;
import org.bson.Document;

import java.io.*;
import java.nio.file.Files;
import java.util.Date;
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
    private static final AtomicBoolean INIT = new AtomicBoolean(false);

    @Override
    public InputStream open(String path) throws FileNotFoundException {
        if (!INIT.get()) {
            INIT.set(true);
            try {
                System.out.println("读取mongo配置数据");
                List<Document> list = MongoDB.query();
             //   File tempFile = File.createTempFile("temp", ".txt");
             //   tempFile.deleteOnExit();

                File tempFile = new File( "/Users/ds/IdeaProjects/config/data/dictionary/custom/mongo.txt");
                // tempFile.deleteOnExit();

                Writer writer = new OutputStreamWriter(Files.newOutputStream(tempFile.toPath()));

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
               // return new FileInputStream(tempFile);
            } catch (Exception e) {
                System.out.println("发生异常: " + e);
            }
           // return null;
        }
        return super.open(path);
    }

    @Override
    public OutputStream create(String path) throws FileNotFoundException {
        System.out.println();
        System.out.println("path = " + path);
        if ("mongo".equals(path)) {
            System.out.println("读取mongo配置数据");
        }
        return super.create(path);
    }
}
