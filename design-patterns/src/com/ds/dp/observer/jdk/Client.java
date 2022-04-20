package com.ds.dp.observer.jdk;

/**
 * @Author ds
 * @Date 2021/3/17 13:52
 * @Description jdk提供的接口，实现观察者模式
 */
public class Client {

    public static void main(String[] args) {
       EmailSubject emailSubject = new EmailSubject();

        for (int i = 0; i < 10; i++) {

            EmailObserver observer = new EmailObserver();
            observer.setEmail("abc" + i + "@d.com");
            emailSubject.addObserver(observer);
        }

        emailSubject.setEmailContent("网站通知《观察者模式》");
    }
}
