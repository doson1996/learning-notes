package com.ds.jvm.chapter01;

import java.util.ArrayList;
import java.util.List;

/**
 * -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=H:\heapdump.hprof -Xms60m -Xmx60m
 * 初始堆大小 -Xms60m
 * 最大堆大小 -Xmx60m
 * -XX:+HeapDumpOnOutOfMemoryError
 * -XX:OnOutOfMemoryError="C:\Program Files\Java\jdk1.8.0_281\bin\jconsole.exe"
 * 可以在OOM后打开jconsole -XX:OnOutOfMemoryError="C:\Program Files\Java\jdk1.8.0_281\bin\jconsole.exe"
 * 可以在OOM后执行脚本用来发送邮件，重启系统   -XX:OnOutOfMemoryError="D:a.bat"
 *
 * @author ds
 * @date 2022/3/2 23:12
 */
public class Demo26HeapDump {
    private byte[] mb = new byte[1 * 1024 * 1024];

    public static void main(String[] args) {

        List<Demo01Heap> list = new ArrayList<>();
        while (true){
            System.out.println(list.size());
            list.add(new Demo01Heap());
        }
    }
}
