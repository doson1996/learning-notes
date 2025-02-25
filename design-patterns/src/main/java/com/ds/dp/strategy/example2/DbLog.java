package com.ds.dp.strategy.example2;

/**
 * @Author ds
 * @Date 2021/3/31 11:08
 * @Description
 */
public class DbLog extends LogStrategyTemplate {

    @Override
    public void doLog(String log) {
        if (log.length() > 5) {
            int i = 10 / 0;
        }

        System.out.println("日志-" + log + "->存入数据库");
    }
}
