package com.ds.dp.strategy.example2;

import java.util.concurrent.TimeUnit;

/**
 * @Author ds
 * @Date 2021/3/31 11:00
 * @Description 和模板方法组合使用，在每次记录日志时添加时间
 */
public class Client {

    public static void main(String[] args) {

        LogContext context = new LogContext();
        context.log("这是日志");
        context.log("这是文件日志");
    }
}
