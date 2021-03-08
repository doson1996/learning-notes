package com.ds.dp.singleton;

import java.io.InputStream;
import java.util.Properties;

/**
 * @Author ds
 * @Date 2021/3/5 17:00
 * @Version 1.0
 * @Description
 */
public class Demo01 {

    public static void main(String[] args) {

        /**
         * 不用单例模式读取properties文件
         * 问题：如果多处需要config.properties里的配置，需要创建很多
         *      AppConfig的实例对象，都是同样的内容，严重浪费系统资源
         */
        AppConfig appConfig1 = new AppConfig();
        AppConfig appConfig2 = new AppConfig();
        System.out.println(appConfig1 == appConfig2);
        System.out.println("appConfig1 = " + appConfig1);
        System.out.println("appConfig2 = " + appConfig2);

        /**
         * 使用单例模式取properties文件
         */
        SingletonAppConfig singletonAppConfig1 = SingletonAppConfig.getInstance();
        SingletonAppConfig singletonAppConfig2 = SingletonAppConfig.getInstance();
        System.out.println(singletonAppConfig1 == singletonAppConfig2);
        System.out.println(singletonAppConfig1);
    }

}

class AppConfig{

    private String conf1;

    private String conf2;

    public AppConfig(){
        readConfig();
    }

    public String getConf1() {
        return conf1;
    }

    public void setConf1(String conf1) {
        this.conf1 = conf1;
    }

    public String getConf2() {
        return conf2;
    }

    public void setConf2(String conf2) {
        this.conf2 = conf2;
    }

    @Override
    public String toString() {
        return "AppConfig{" +
                "conf1='" + conf1 + '\'' +
                ", conf2='" + conf2 + '\'' +
                '}';
    }

    public void readConfig(){

        Properties properties = new Properties();
        InputStream is = null;

        try {
            is = AppConfig.class.getResourceAsStream("config.properties");
            properties.load(is);
            this.conf1 = properties.getProperty("conf1");
            this.conf2 = properties.getProperty("conf1");
        } catch (Exception e) {

            e.printStackTrace();
        }

    }
}

class SingletonAppConfig{

    private static SingletonAppConfig instance = new SingletonAppConfig();

    private String conf1;

    private String conf2;

    private SingletonAppConfig(){
        readConfig();
    }

    public static SingletonAppConfig getInstance(){
        return instance;
    }

    public String getConf1() {
        return conf1;
    }

    public void setConf1(String conf1) {
        this.conf1 = conf1;
    }

    public String getConf2() {
        return conf2;
    }

    public void setConf2(String conf2) {
        this.conf2 = conf2;
    }

    @Override
    public String toString() {
        return "SingletonAppConfig{" +
                "conf1='" + conf1 + '\'' +
                ", conf2='" + conf2 + '\'' +
                '}';
    }

    public void readConfig(){

        Properties properties = new Properties();
        InputStream is = null;

        try {
            is = AppConfig.class.getResourceAsStream("config.properties");
            properties.load(is);
            this.conf1 = properties.getProperty("conf1");
            this.conf2 = properties.getProperty("conf1");
        } catch (Exception e) {

            e.printStackTrace();
        }

    }
}