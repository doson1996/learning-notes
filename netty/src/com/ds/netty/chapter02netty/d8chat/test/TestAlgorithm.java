package com.ds.netty.chapter02netty.d8chat.test;

import com.ds.netty.chapter02netty.d8chat.config.Config;
import com.ds.netty.chapter02netty.d8chat.message.LoginRequestMessage;
import com.ds.netty.chapter02netty.d8chat.message.Message;
import com.ds.netty.chapter02netty.d8chat.protocol.MessageCodec;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author ds
 * @date 2023/7/13
 * @description
 */
public class TestAlgorithm {
    public static void main(String[] args) {
        MessageCodec MESSAGE_CODEC = new MessageCodec();
        LoggingHandler LOGGING_HANDLER = new LoggingHandler(LogLevel.DEBUG);
        final EmbeddedChannel embeddedChannel = new EmbeddedChannel(
                LOGGING_HANDLER,
                MESSAGE_CODEC,
                LOGGING_HANDLER
        );
        LoginRequestMessage message = new LoginRequestMessage("zhangsan", "123456");

        /* ########################################################
           #######    出站测试  【出站 自动编码】  encode   ##########
           ########################################################*/
        embeddedChannel.writeOutbound(message);

        /* #########################################################
           #######    入站测试   【入站 自动解码】  decode ############
           #########################################################*/
        final ByteBuf buf = messageToByteBuf(message);
        embeddedChannel.writeInbound(buf);


    }

    private static ByteBuf messageToByteBuf(Message msg) {
        ByteBuf out = ByteBufAllocator.DEFAULT.buffer();
        out.writeBytes(new byte[]{1, 2, 3, 4}); // 4字节的 魔数
        out.writeByte(1);                    // 1字节的 版本
        out.writeByte(Config.getMySerializerAlgorithm().ordinal()); // 1字节的 序列化方式 0-jdk,1-json
        out.writeByte(msg.getMessageType()); // 1字节的 指令类型
        out.writeInt(msg.getSequenceId());   // 4字节的 请求序号 【大端】
        out.writeByte(0xff);                 // 1字节的 对其填充，只为了非消息内容 是2的整数倍
        final byte[] bytes = Config.getMySerializerAlgorithm().serializ(msg);
        // 写入内容 长度
        out.writeInt(bytes.length);
        // 写入内容
        out.writeBytes(bytes);
        return out;
    }
}
