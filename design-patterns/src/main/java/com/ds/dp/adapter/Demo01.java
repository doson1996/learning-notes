package com.ds.dp.adapter;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author ds
 * @Date 2021/3/5 14:28
 * @Version 1.0
 * @Description 适配器模式
 */
public class Demo01 {

    public static void main(String[] args) throws FileNotFoundException {

        LogOperateApi logOperateApi = new LogOperate();

        List<LogModel> logs = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            LogModel log = new LogModel("" + i,"日志内容" + i);
            logs.add(log);
        }

        logOperateApi.write(logs);

        List<String> logs1 = logOperateApi.read();
        System.out.println("logs1 = " + logs1);

    }
}
