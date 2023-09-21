package com.ds.ctp.batch.service.syncisas.operate;

import com.alibaba.fastjson2.JSONObject;
import com.ds.ctp.batch.mapper.BaseMapper;

/**
 * @author ds
 * @date 2023/9/21
 * @description
 */
public abstract class BaseOperation<T> implements Operation {

    private final BaseMapper<T> mapper;

    private final Class<T> clazz;

    protected BaseOperation() {
        this.mapper = getMapper();
        this.clazz = getClazz();
    }

    protected abstract BaseMapper<T> getMapper();

    protected abstract Class<T> getClazz();

    @Override
    public boolean insert(JSONObject data) {
        onInsert(data);
        return mapper.insert(data.to(clazz)) > 0;
    }

    @Override
    public boolean update(JSONObject data) {
        onUpdate(data);
        return mapper.update(data.to(clazz), data.getJSONObject("condMap").to(clazz)) > 0;
    }

    @Override
    public boolean delete(JSONObject data) {
        onDelete(data);
        return mapper.delete(data.getJSONObject("condMap").to(clazz)) > 0;
    }

    protected void onInsert(JSONObject date) {

    }

    protected void onUpdate(JSONObject date) {

    }

    protected void onDelete(JSONObject date) {

    }

}
