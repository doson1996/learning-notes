package com.ds.dp.strategy.example2;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author ds
 * @Date 2021/3/31 11:02
 * @Description
 */
public abstract class LogStrategyTemplate implements LogStrategy{

    @Override
    public final void log(String log) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format(new Date());
        log = time + "-->" + log;
        doLog(log);
    }

    /**
     * 真正执行日志记录，让子类去实现
     * @param log
     */
    public abstract void doLog(String log);
}
