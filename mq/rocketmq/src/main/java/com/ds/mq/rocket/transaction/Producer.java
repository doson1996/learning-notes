package com.ds.mq.rocket.transaction;

import com.ds.mq.rocket.config.Config;
import org.apache.rocketmq.client.producer.*;
import org.apache.rocketmq.common.message.Message;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.*;

/**
 * 发送事务消息
 * @author ds
 */
public class Producer {
    public static void main(String[] args) throws Exception {
        TransactionListener transactionListener = new TransactionListenerImpl();

        TransactionMQProducer producer = new TransactionMQProducer("group_trx_producer");
        producer.setNamesrvAddr(Config.NAME_SERVER_ADDRESSES);

        ExecutorService executorService = new ThreadPoolExecutor(2, 5, 100, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(2000), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setName("client-transaction-msg-check-thread");
                return thread;
            }
        });
        producer.setExecutorService(executorService);
        producer.setTransactionListener(transactionListener);
        producer.start();

        Message message = new Message();
        message.setTopic("Topic_Trx");
        message.setTags("Tag_Trx");
        message.setKeys("Key_Trx");
        message.setBody(("hello rocketmq trx").getBytes(StandardCharsets.UTF_8));

        TransactionSendResult result = producer.sendMessageInTransaction(message, null);
        System.out.println("result = " + result);

        producer.shutdown();
    }
}
