package com.ds.ctp.batch.service.syncisas.operate;

import com.ds.ctp.batch.bean.CtpUser;
import com.ds.ctp.batch.mapper.BaseMapper;
import com.ds.ctp.batch.mapper.impl.CtpUserMapperImpl;

/**
 * @author ds
 * @date 2023/9/21
 * @description ctp_user操作类
 */
public class CtpUserOperation extends BaseOperation<CtpUser> {

    @Override
    protected BaseMapper<CtpUser> getMapper() {
        return new CtpUserMapperImpl();
    }

    @Override
    protected Class<CtpUser> getClazz() {
        return CtpUser.class;
    }

}
