package com.ds.dp.interpreter.unused;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

/**
 * @Author ds
 * @Date 2021/4/7 9:44
 * @Description
 */
public class Client {

    public static void main(String[] args) throws IOException {
        InputStream is = Client.class.getResourceAsStream("application.xml");
        ReadAppXml readAppXml = new ReadAppXml();
        readAppXml.read(is);

    }
}
