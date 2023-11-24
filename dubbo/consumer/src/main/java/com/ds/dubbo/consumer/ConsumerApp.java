package com.ds.dubbo.consumer;

import com.ds.dubbo.provider.stub.HelloService;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;

/**
 * @author ds
 * @date 2023/11/24
 * @description
 */
public class ConsumerApp {

    private static final String ZOOKEEPER_ADDRESS = "zookeeper://47.98.217.92:2181";

    public static void main(String[] args) {
        // 定义所有的订阅
        ReferenceConfig<HelloService> reference = new ReferenceConfig<>();
        reference.setInterface(HelloService.class);

        // 启动 Dubbo
        DubboBootstrap.getInstance()
                .application("dubbo-consumer")
                .registry(new RegistryConfig(ZOOKEEPER_ADDRESS))
                .reference(reference)
                .start();



        // 像普通的 java 接口一样调用
        for (int i = 0; i < 10; i++) {
            // 获取订阅到的 Stub
            HelloService service = reference.get();
            String res = service.sayHi("dubbo");
            System.out.println("res = " + res);
        }
    }
}
