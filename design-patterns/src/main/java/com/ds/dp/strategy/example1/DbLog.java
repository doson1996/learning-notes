package com.ds.dp.strategy.example1;

/**
 * @Author ds
 * @Date 2021/3/31 10:48
 * @Description 写入数据库
 */
public class DbLog implements LogStrategy {

    @Override
    public void write(String log) {
        if (log.length() > 5) {
            int i = 10 / 0;
        }

        System.out.println("日志-" + log + "->存入数据库");
    }
}
