package com.ds.dp.strategy.example1;

/**
 * @Author ds
 * @Date 2021/3/31 10:46
 * @Description 容错恢复机制
 */
public class Client {

    public static void main(String[] args) {
        String log = "这是日志";
        LogContext context = new LogContext();
        context.log(log);
        context.log("这是日志11");

    }
}
