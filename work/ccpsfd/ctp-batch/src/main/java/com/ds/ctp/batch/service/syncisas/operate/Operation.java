package com.ds.ctp.batch.service.syncisas.operate;

import com.alibaba.fastjson2.JSONObject;

/**
 * @author ds
 * @date 2023/9/19
 * @description
 */
public interface Operation {

    /**
     * 新增
     * @param data
     * @return
     */
    boolean insert(JSONObject data);

    /**
     * 更新
     * @param data
     * @return
     */
    boolean update(JSONObject data);

    /**
     * 删除
     * @param data
     * @return
     */
    boolean delete(JSONObject data);

}
