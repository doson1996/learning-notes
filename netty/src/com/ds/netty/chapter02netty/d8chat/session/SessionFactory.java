package com.ds.netty.chapter02netty.d8chat.session;

/**
 * @author ds
 * @date 2023/7/10
 * @description
 */
public abstract class SessionFactory {
    private static Session session = new SessionMemoryImpl();

    public static Session getSession() {
        return session;
    }
}
