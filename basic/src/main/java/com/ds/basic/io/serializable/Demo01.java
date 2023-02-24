package com.ds.basic.io.serializable;

import java.io.*;

/**
 * @Author ds
 * @Date 2021/3/17 9:49
 * @Description
 */
public class Demo01 {

    public static void main(String[] args) throws Exception {

        write();
        read();
    }

    public static void write() throws Exception{

        User user = new User("张三","abc@d.com");
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("user.txt"));
        os.writeObject(user);
        os.close();
    }

    public static void read() throws Exception{

        ObjectInputStream oi = new ObjectInputStream(new FileInputStream("user.txt"));
        User readUser = (User)oi.readObject();
        System.out.println(readUser);
        oi.close();
    }
}
