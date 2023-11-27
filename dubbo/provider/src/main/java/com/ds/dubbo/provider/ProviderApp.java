package com.ds.dubbo.provider;

import com.ds.dubbo.provider.service.HelloServiceImplV1;
import com.ds.dubbo.provider.stub.HelloService;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;

/**
 * @author ds
 * @date 2023/11/24
 * @description
 */
public class ProviderApp {

    private static final String ZOOKEEPER_ADDRESS = "zookeeper://ds.com:2181";

    public static void main(String[] args) throws Exception {
        // 定义所有的服务
        ServiceConfig<HelloService> service = new ServiceConfig<>();
        service.setInterface(HelloService.class);
        service.setRef(new HelloServiceImplV1());

        // 启动 Dubbo
        DubboBootstrap.getInstance()
                .application("dubbo-provider")
                .registry(new RegistryConfig(ZOOKEEPER_ADDRESS))
                .protocol(new ProtocolConfig("dubbo", -1))
                .service(service)
                .start();

        System.out.println("dubbo-provider is running.");
        System.in.read();
    }
}
