package com.ds.ctp.batch.mapper.impl;

import com.ds.ctp.batch.bean.CtpUser;
import com.ds.ctp.batch.mapper.CtpUserMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * @author ds
 * @date 2023/9/21
 * @description
 */
@Slf4j
public class CtpUserMapperImpl implements CtpUserMapper {
    @Override
    public int insert(CtpUser data) {
        log.info("ctp_user表执行insert操作, data: {}", data);
        return data != null ? 1 : 0;
    }

    @Override
    public int delete(CtpUser condition) {
        log.info("ctp_user表执行delete操作, condition: {}", condition);
        return condition != null ? 1 : 0;
    }

    @Override
    public int update(CtpUser data, CtpUser condition) {
        log.info("ctp_user表执行delete操作,data: {}, condition: {}", data, condition);
        return data != null && condition != null ? 1 : 0;
    }
}
