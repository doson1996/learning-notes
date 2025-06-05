package com.ds.springframework.aop;

/**
 * @author ds
 * @date 2025/5/29
 * @description
 */
public class DsService implements IService {
    @Override
    public String toString() {
        System.out.println("dsService toString invoke");
        return "dsService";
    }

    public void testAop(){
        System.out.println("testAop invoke");
    }

    @Override
    public void say() {
        System.out.println("say...");
    }
}
