package com.ds.ctp.batch.service;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.ds.ctp.batch.service.syncisas.executor.Executor;
import com.ds.ctp.batch.service.syncisas.executor.SimpleExecutor;
import com.ds.ctp.batch.service.syncisas.factory.OperationFactory;
import com.ds.ctp.batch.service.syncisas.operate.BaseOperation;

import java.util.List;

/**
 * @author ds
 * @date 2023/9/19
 * @description
 */
public class SyncIsasService {

    private final Executor executor;

    public SyncIsasService() {
        this.executor = new SimpleExecutor();
    }

    /**
     * 模拟dmqs，收到消息
     *
     * @param records
     */
    public void execute(List<JSONArray> records) {
        kafkaMsg(records);
        for (JSONArray record : records) {
            for (int i = 0; i < record.size(); i++) {
                JSONObject tableInfo = record.getJSONObject(i);
                String tableName = tableInfo.getString("tableName");
                String operate = tableInfo.getString("operate");
                JSONObject data = tableInfo.getJSONObject("data");
                // 获取操作类
                BaseOperation operation = OperationFactory.createOperation(tableName);
                // 委托执行器执行
                executor.execute(operation, data, operate);
            }
        }
    }

    /**
     * 模拟接收到kafka消息
     *
     * @param records
     */
    public void kafkaMsg(List<JSONArray> records) {
        JSONArray jsonArray = new JSONArray();
        JSONObject ctpUser = new JSONObject();
        ctpUser.put("tableName", "CTP_USER");
        ctpUser.put("operate", "INSERT");
        JSONObject data = new JSONObject();
        data.put("id", "10001");
        data.put("branchId", "101");
        data.put("username", "张三");
        data.put("password", "123456");
        ctpUser.put("data", data);
        jsonArray.add(ctpUser);
        records.add(jsonArray);
    }

}
