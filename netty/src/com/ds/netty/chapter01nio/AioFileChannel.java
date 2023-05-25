package com.ds.netty.chapter01nio;

import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.CountDownLatch;

/**
 * @author ds
 * @date 2023/5/25
 * @description
 */
@Slf4j
public class AioFileChannel {

    private static final CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) {
        try (AsynchronousFileChannel channel = AsynchronousFileChannel.open(Paths.get("a.txt"), StandardOpenOption.READ)) {

            ByteBuffer buffer = ByteBuffer.allocate(16);
            log.info("开始读取文件...");
            channel.read(buffer, 0, buffer, new CompletionHandler<Integer, ByteBuffer>() {

                @Override
                public void completed(Integer result, ByteBuffer attachment) {
                    log.info("读取到文件大小{}字节", result);
                    attachment.flip();
                    BufferUtils.print(attachment);
                    countDownLatch.countDown();
                }

                @Override
                public void failed(Throwable exc, ByteBuffer attachment) {
                    log.error("读取文件发生异常: ", exc);
                }
            });

            countDownLatch.await();
            log.info("完成读取文件...");
        } catch (Exception e) {
            log.error("main ex: ", e);
        }
    }
}
