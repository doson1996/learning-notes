package com.ds.dp.adapter;

import java.util.List;

/**
 * @Author ds
 * @Date 2021/3/5 16:29
 * @Version 1.0
 * @Description 类适配器
 */
public class ClassAdapter extends LogOperate implements LogDbOperateApi {

    @Override
    public void insert(LogModel log) {

        List<String> logs = this.read();
    }

    @Override
    public List<LogModel> getLogs() {
        return this.getLogs();
    }
}
