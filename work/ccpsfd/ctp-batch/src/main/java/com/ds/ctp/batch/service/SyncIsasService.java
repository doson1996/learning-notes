package com.ds.ctp.batch.service;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;

import java.util.List;

/**
 * @author ds
 * @date 2023/9/19
 * @description
 */
public class SyncIsasService {

    /**
     * 模拟dmqs，收到消息
     * @param records
     */
    public void execute(List<JSONArray> records) {
        kafkaMsg(records);
        for (JSONArray record : records) {
            for (int i = 0; i < record.size(); i++) {
                JSONObject tableInfo = record.getJSONObject(i);
                String tableName = tableInfo.getString("tableName");
                String operate = tableInfo.getString("operate");
                String data = tableInfo.getString("data");

            }
        }

    }

    /**
     * 模拟接收到kafka消息
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
