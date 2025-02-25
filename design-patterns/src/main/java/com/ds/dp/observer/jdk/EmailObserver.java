package com.ds.dp.observer.jdk;

import java.util.Observable;
import java.util.Observer;

/**
 * @Author ds
 * @Date 2021/3/17 13:56
 * @Description 邮箱观察者
 */
public class EmailObserver implements Observer {

    private String email;

    @Override
    public void update(Observable o, Object arg) {
        EmailSubject emailSubject = (EmailSubject) o;
        System.out.println(email + "--收到邮件--" + emailSubject.getEmailContent());
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
