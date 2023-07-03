package com.ds.netty.chapter02netty.d7protocol.ciz;

/**
 * @author ds
 * @date 2023/7/3
 * @description
 */
public abstract class AbstractMessage implements Message {

    public static final int LOGIN_REQUEST = 0;

    public static final int LOGIN_RESPONSE = 1;

    private int sequenceId;

    @Override
    public int getSequenceId() {
        return sequenceId;
    }


}
