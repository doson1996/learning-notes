package com.ds.case1;

/**
 * @author ds
 */
public class DMQSProducer extends KafkaProducer {

    public DMQSProducer() {
        super();
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " | 监控...");
            }
        });
        thread.setDaemon(true);
        thread.start();
    }
}
