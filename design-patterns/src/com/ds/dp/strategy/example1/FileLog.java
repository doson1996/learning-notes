package com.ds.dp.strategy.example1;

/**
 * @Author ds
 * @Date 2021/3/31 10:48
 * @Description 写入文件
 */
public class FileLog implements LogStrategy{

    @Override
    public void write(String log) {
        System.out.println("日志-" + log + "->存入文件");
    }
}
