package com.ds.mq.rocket.transaction;

import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ds
 */
public class TransactionListenerImpl implements TransactionListener {

    private ConcurrentHashMap<String, Integer> localTrans = new ConcurrentHashMap<>();

    /**
     * 执行本地事务
     * @param message
     * @param o
     * @return
     */
    @Override
    public LocalTransactionState executeLocalTransaction(Message message, Object o) {
        String transactionId = message.getTransactionId();
        //0：执行失败 1：执行成功 2.执行中
        localTrans.put(transactionId, 2);

        //执行本地事务 Service
        try {
            System.out.println("事务" + transactionId + "执行中...");
            Thread.sleep(120000);
            localTrans.put(transactionId, 1);
            System.out.println("事务" + transactionId + "执行成功...");
        } catch (Exception e) {
            localTrans.put(transactionId, 0);
            System.out.println("事务" + transactionId + "执行失败...");
            e.printStackTrace();
            return LocalTransactionState.ROLLBACK_MESSAGE;
        }

        return LocalTransactionState.COMMIT_MESSAGE;
    }

    /**
     * 消息回查
     * @param messageExt
     * @return
     */
    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {
        Integer status = localTrans.get(messageExt.getTransactionId());
        System.out.println("消息回查 事务id: " + messageExt.getTransactionId() + " 状态 = "  + status);
        if (null != status) {
            switch (status) {
                case 0:
                    return LocalTransactionState.ROLLBACK_MESSAGE;
                case 1:
                    return LocalTransactionState.COMMIT_MESSAGE;
                case 2:
                    return LocalTransactionState.UNKNOW;
                default:
                    return LocalTransactionState.UNKNOW;
            }
        }

        return LocalTransactionState.UNKNOW;
    }
}
