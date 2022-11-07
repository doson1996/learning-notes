package com.ds.case1;

/**
 * 模拟kafka消息生产者
 * @author ds
 */
public class KafkaProducer implements Producer {

    @Override
    public boolean send(String msg) {
        //模拟发送消息，消息不等于null发送成功
        return msg != null;
    }

}
