package com.ds.jvm.chapter01;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author ds
 * @Date 2021/3/12 17:12
 * @Description 初始堆大小 -Xms10m
 *              最大堆大小 -Xmx10m
 *              可以在OOM后打开jconsole -XX:OnOutOfMemoryError="C:\Program Files\Java\jdk1.8.0_281\bin\jconsole.exe"
 *              可以在OOM后执行脚本用来发送邮件，重启系统   -XX:OnOutOfMemoryError="D:a.bat"
 */
public class Demo01Heap {

    private byte[] _1MB = new byte[1 * 1024 * 1024];

    public static void main(String[] args) {

        System.out.println("java.version = " + System.getProperty("java.version"));

        List<Demo01Heap> list = new ArrayList<>();
        while (true){
            System.out.println(list.size());
            list.add(new Demo01Heap());
        }

    }
}
