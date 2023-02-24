package com.ds.dp.strategy.example2;

/**
 * @Author ds
 * @Date 2021/3/31 11:09
 * @Description
 */
public class FileLog extends LogStrategyTemplate{

    @Override
    public void doLog(String log) {
        System.out.println("日志-" + log + "->存入文件");
    }
}
