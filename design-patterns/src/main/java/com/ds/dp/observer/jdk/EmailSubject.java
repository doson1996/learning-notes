package com.ds.dp.observer.jdk;

import java.util.Observable;

/**
 * @Author ds
 * @Date 2021/3/17 13:55
 * @Description
 */
public class EmailSubject extends Observable {

    private String emailContent;

    public String getEmailContent() {
        return emailContent;
    }

    public void setEmailContent(String emailContent) {
        this.emailContent = emailContent;
        setChanged();
        notifyObservers();
    }
}
