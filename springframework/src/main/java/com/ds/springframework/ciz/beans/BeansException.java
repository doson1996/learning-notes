package com.ds.springframework.ciz.beans;

/**
 * @author ds
 * @date 2024/12/5
 * @description
 */
public class BeansException extends RuntimeException {

    public BeansException() {
    }

    public BeansException(String message) {
        super(message);
    }

    public BeansException(String message, Throwable cause) {
        super(message, cause);
    }
}
