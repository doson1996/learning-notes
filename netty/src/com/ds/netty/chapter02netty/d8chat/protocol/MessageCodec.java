package com.ds.netty.chapter02netty.d8chat.protocol;

import com.ds.netty.chapter02netty.d8chat.message.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * @author ds
 * @date 2023/7/3
 * @description
 */
@Slf4j
@ChannelHandler.Sharable
public class MessageCodec extends MessageToMessageCodec<ByteBuf, Message> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Message msg, List<Object> out) throws Exception {
        ByteBuf buffer = ctx.alloc().buffer();
        // 设置魔数 4个字节
        buffer.writeBytes(new byte[]{'C', 'H', 'A', 'T'});
        // 设置版本号 1个字节
        buffer.writeByte(1);
        // 设置序列化方式 1个字节
        buffer.writeByte(1);
        // 设置指令类型 1个字节
        buffer.writeByte(msg.getMessageType());
        // 设置请求序号 4个字节
        buffer.writeInt(msg.getSequenceId());
        // 为了补齐为16个字节，填充1个字节的数据
        buffer.writeByte(0xff);

        // 获得序列化后的msg
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(msg);
        byte[] bytes = bos.toByteArray();

        // 获得并设置正文长度 长度用4个字节标识
        buffer.writeInt(bytes.length);
        // 设置消息正文
        buffer.writeBytes(bytes);

        out.add(buffer);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        int magic = msg.readInt();
        // 获取版本号
        byte version = msg.readByte();
        // 获得序列化方式
        byte seqType = msg.readByte();
        // 获得指令类型
        byte messageType = msg.readByte();
        // 获得请求序号
        int sequenceId = msg.readInt();
        // 移除补齐字节
        msg.readByte();
        // 获得正文长度
        int length = msg.readInt();
        // 获得正文
        byte[] bytes = new byte[length];
        msg.readBytes(bytes, 0, length);
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
        Message message = (Message) ois.readObject();
        // 将信息放入List中，传递给下一个handler
        out.add(message);
    }
}
