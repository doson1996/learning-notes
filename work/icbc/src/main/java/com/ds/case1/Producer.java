package com.ds.case1;

/**
 * 模拟kafka生产者接口
 * @author ds
 */
public interface Producer {

    /**
     * 发送消息
     * @param msg
     * @return
     */
    boolean send(String msg);

}
