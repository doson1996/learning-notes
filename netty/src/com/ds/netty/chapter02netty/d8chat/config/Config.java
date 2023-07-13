package com.ds.netty.chapter02netty.d8chat.config;

import com.ds.netty.chapter02netty.d8chat.protocol.MySerializer;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author ds
 * @date 2023/7/13
 * @description
 */
public class Config {
    static Properties properties;

    static {
        try (InputStream in = Config.class.getResourceAsStream("/application.properties")) {
            properties = new Properties();
            properties.load(in);
        } catch (IOException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static int getServerPort() {
        final String value = properties.getProperty("server.port");
        if (value == null) {
            return 8080;
        } else {
            return Integer.parseInt(value);
        }
    }

    public static MySerializer.Algorithm getMySerializerAlgorithm() {
        final String value = properties.getProperty("mySerializer.algorithm");
        MySerializer.Algorithm algorithm = MySerializer.Algorithm.valueOf(value);
        if (algorithm != null) {
            return algorithm;
        } else {
            return MySerializer.Algorithm.Java;
        }
    }
}
