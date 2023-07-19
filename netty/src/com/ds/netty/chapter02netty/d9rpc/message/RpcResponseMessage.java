package com.ds.netty.chapter02netty.d9rpc.message;

import com.ds.netty.chapter02netty.d8chat.message.Message;
import lombok.Data;
import lombok.ToString;

/**
 * @author ds
 * @date 2023/7/19
 * @description
 */
@Data
@ToString(callSuper = true)
public class RpcResponseMessage extends Message {

    /**
     * 返回值
     */
    private Object returnValue;
    /**
     * 异常值
     */
    private Exception exceptionValue;

    @Override
    public int getMessageType() {
        return RPC_MESSAGE_TYPE_RESPONSE;
    }

}
