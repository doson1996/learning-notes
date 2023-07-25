package com.ds.netty.chapter02netty.d9rpc.handler;

import com.ds.netty.chapter02netty.d9rpc.message.RpcResponseMessage;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.concurrent.Promise;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ds
 * @date 2023/7/20
 * @description
 */
@Slf4j
@ChannelHandler.Sharable
public class RpcResponseMessageHandler extends SimpleChannelInboundHandler<RpcResponseMessage> {

    public static final Map<Integer, Promise<Object>> PROMISES = new ConcurrentHashMap<>();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RpcResponseMessage msg) throws Exception {
        log.info("msg: {}", msg);
        final Promise<Object> promise = PROMISES.remove(msg.getSequenceId()); // 获取 并销毁值
        if (promise != null) {
            final Object returnValue = msg.getReturnValue();         // 正常结果
            final Exception exceptionValue = msg.getExceptionValue();// 异常结果 【约定 为 null才是正常的】
            if (exceptionValue != null) {
                promise.setFailure(exceptionValue);

            } else {
                promise.setSuccess(returnValue);
            }
        }
    }
}
