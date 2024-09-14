package com.ds.concurrent.chapter01;

/**
 * @Author ds
 * @Date 2021/3/12 11:34
 * @Description volatile信号量   - 保证变量的内存可见性 - 禁止指令重排序
 */
public class Demo02Volatile {

    public static void main(String[] args) {

        ThreadVolatile thread = new ThreadVolatile();
        thread.start();
        while (true) {

            if (thread.isFlag()) {
                System.out.println(" main -> flag -> true");
            }

        }

    }


    private static class ThreadVolatile extends Thread {

        private volatile boolean flag;

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            flag = true;
        }

        public boolean isFlag() {
            return flag;
        }
    }

}

