package com.ds.concurrent.threadpool.cancel;

/**
 * @author ds
 * @date 2024/6/25
 * @description
 */
public class Task implements Runnable {

    private final String taskId;

    public Task(String taskId) {
        this.taskId = taskId;
    }

    @Override
    public void run() {
        try {
            while (true) {
                System.out.println("taskId = " + taskId + "的任务执行开始...");
                Thread.sleep(2000);
                System.out.println("taskId = " + taskId + "的任务执行完成...");
                if (Thread.currentThread().isInterrupted()) {
                    return;
                }
            }

        } catch (Exception e) {
//            System.out.println("");
        }
        System.out.println("taskId = " + taskId + "的任务执行结束...");
    }
}
