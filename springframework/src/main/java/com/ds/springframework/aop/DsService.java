package com.ds.springframework.aop;

/**
 * @author ds
 * @date 2025/5/29
 * @description
 */
public class DsService {
    @Override
    public String toString() {
        System.out.println("dmzService toString invoke");
        return "dmzService";
    }

    public void testAop(){
        System.out.println("testAop invoke");
    }

}
