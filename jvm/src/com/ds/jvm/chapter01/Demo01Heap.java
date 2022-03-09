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
 *              查看当前使用的垃圾收集器   -XX:+PrintCommandLineFlags -version
 */
public class Demo01Heap {

    private byte[] mb = new byte[1 * 1024 * 1024];

    public static void main(String[] args) {

        List<Demo01Heap> list = new ArrayList<>();
        while (true) {
          //  System.out.println(list.size());
            list.add(new Demo01Heap());
        }

    }
}
