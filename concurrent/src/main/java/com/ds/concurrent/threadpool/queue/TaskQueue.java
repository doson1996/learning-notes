package com.ds.concurrent.threadpool.queue;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author ds
 * @date 2026/5/12
 * @description 改变线程池执行流程 先把线程数用满，再放到队列
 */
public class TaskQueue<E> extends LinkedBlockingQueue<E> {

    public TaskQueue(int capacity) {
        super(capacity);
    }

    private transient volatile ThreadPoolExecutor parent = null;

    public void setParent(ThreadPoolExecutor threadPool) {
        this.parent = threadPool;
    }

    @Override
    public boolean offer(E e) {
        if (this.parent == null) {
            return super.offer(e);
        } else if (this.parent.getPoolSize() == this.parent.getMaximumPoolSize()) {  // 线程数已到最大值，只能入队列
            return super.offer(e);
        } else if (this.parent.getActiveCount() < this.parent.getPoolSize()) {  // 还有空闲线程，入队列让空闲线程去取
            return super.offer(e);
        } else {
            // 线程数没到最大值，返回false触发创建新线程
            return this.parent.getPoolSize() >= this.parent.getMaximumPoolSize() && super.offer(e);
        }
    }
}
