package com.ds.dp.adapter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author ds
 * @Date 2021/3/5 14:54
 * @Version 1.0
 * @Description 日志操作接口实现类
 */
public class LogOperate implements LogOperateApi {

    private String fileName = LogOperate.class.getResource("/").getPath() + "log.txt";

    @Override
    public void write(List<LogModel> logs) {

        FileWriter writer = null;

        try {

            writer = new FileWriter(fileName);
            for (LogModel log : logs) {
                writer.write(log.toString());
                writer.write("\r\n");
            }
        } catch (Exception e) {

            e.printStackTrace();
        } finally {

            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public List<String> read() {

        List<String> logs = new ArrayList<>();
        BufferedReader reader = null;

        try {

            reader = new BufferedReader(new FileReader(fileName));
            while (true) {
                String log = reader.readLine();
                if (log == null) break;
                logs.add(log);
            }
        } catch (Exception e) {

            e.printStackTrace();
        } finally {

            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return logs;
    }
}
