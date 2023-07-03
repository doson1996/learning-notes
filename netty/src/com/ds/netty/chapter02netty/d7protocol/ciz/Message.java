package com.ds.netty.chapter02netty.d7protocol.ciz;

import java.io.Serializable;

/**
 * @author ds
 * @date 2023/7/3
 * @description
 */
public interface Message extends Serializable {

    /**
     * 获取消息类型
     * @return
     */
    int getType();

    int getSequenceId();

}
