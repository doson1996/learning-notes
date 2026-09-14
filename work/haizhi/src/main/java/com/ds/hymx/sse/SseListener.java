package com.ds.hymx.sse;

/**
 * @author ds
 * @date 2026/9/14
 * @description
 */
public interface SseListener {

    /** 连接建立成功时回调 */
    void onOpen();

    /** 收到一个完整 SSE 事件时回调 */
    void onEvent(SseEvent event);

    /** 连接关闭时回调（正常关闭） */
    void onClosed();

    /** 发生异常时回调，返回 true 表示需要重连 */
    void onError(Throwable t);

}
