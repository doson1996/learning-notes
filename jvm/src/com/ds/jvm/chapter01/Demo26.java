package com.ds.jvm.chapter01;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

/**
 * @author ds
 */
public class Demo26 {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress ia = InetAddress.getLocalHost();
        System.out.println(ia.toString());
        System.out.println(ia.getHostName());//域名               127
        System.out.println(ia.getHostAddress());//ip地址           192.168.201.254
        System.out.println(Arrays.toString(ia.getAddress()));//-64, -88, 6, -2(最高位是1)
    }
}
