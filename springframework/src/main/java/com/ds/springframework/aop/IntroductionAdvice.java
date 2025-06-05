package com.ds.springframework.aop;

import org.springframework.aop.support.DelegatingIntroductionInterceptor;

/**
 * @author ds
 * @date 2025/5/30
 * @description
 */
public class IntroductionAdvice extends DelegatingIntroductionInterceptor implements IService {
    @Override
    public void say() {
        System.out.println("say...");
    }
}
