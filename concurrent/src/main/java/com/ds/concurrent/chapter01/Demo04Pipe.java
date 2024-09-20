package com.ds.concurrent.chapter01;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

/**
 * @Author ds
 * @Date 2021/3/12 14:21
 * @Description 线程通信—-> 管道
 */
public class Demo04Pipe {

    public static void main(String[] args) throws IOException {
        PipedWriter writer = new PipedWriter();
        PipedReader reader = new PipedReader();
        reader.connect(writer);

        new Thread(new ThreadA(writer)).start();
        new Thread(new ThreadB(reader)).start();

    }

    private static class ThreadA implements Runnable {
        private PipedWriter writer;

        public ThreadA(PipedWriter writer) {
            this.writer = writer;
        }

        @Override
        public void run() {

            try {
                writer.write("pipe test");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (writer != null) {
                    try {
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        public PipedWriter getWriter() {
            return writer;
        }

        public void setWriter(PipedWriter writer) {
            this.writer = writer;
        }

    }


    private static class ThreadB implements Runnable {

        private PipedReader reader;

        public ThreadB(PipedReader reader) {
            this.reader = reader;
        }

        @Override
        public void run() {

            int v;
            try {
                while ((v = reader.read()) != -1) {
                    System.out.print((char) v);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        public PipedReader getReader() {
            return reader;
        }

        public void setReader(PipedReader reader) {
            this.reader = reader;
        }

    }

}
