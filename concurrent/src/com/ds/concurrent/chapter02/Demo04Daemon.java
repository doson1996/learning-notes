package com.ds.concurrent.chapter02;

import java.util.concurrent.TimeUnit;

/**
 * @Author ds
 * @Date 2021/4/1 17:33
 * @Description
 *          注意： 在构建 Daemon 线程时，不能依靠 finally 块中的内容来确保执行关闭或清理资源的逻辑
 *
 *         main 线程（非 Daemon 线程）在启动了线程 DaemonRunner 之后随着 main 方法执行完毕而终止，
 *        而此时 Java 虚拟机中已经没有非 Daemon 线程，虚拟机需要退出。 Java 虚拟机中的所有Daemon 线程都需要立即终止，
 *        因此 DaemonRunner 立即终止，但是 DaemonRunner 中的finally 块并没有执行
 */
public class Demo04Daemon {

    public static void main(String[] args) {

        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("DaemonThread finally run");
            }
        });
        thread.setDaemon(true);
        thread.start();
    }
}
