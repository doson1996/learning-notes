package com.ds.dp.responsibility.example;

/**
 * @Author ds
 * @Date 2021/4/9 10:46
 * @Description
 */
public class Client {

    public static void main(String[] args) {

        Handler pm = new ProjectManager();
        Handler dm = new DepManager();
        Handler gm = new GeneralManager();

        pm.setHandler(dm);
        dm.setHandler(gm);

        String res1 = pm.handlerFeeRequest("张三", 300);
        String res2 = pm.handlerFeeRequest("李四", 300);

        String res3 = pm.handlerFeeRequest("张三", 600);
        String res4 = pm.handlerFeeRequest("李四", 600);

        String res5 = pm.handlerFeeRequest("张三", 1300);
        String res6 = pm.handlerFeeRequest("李四", 1300);

        System.out.println("res1 = " + res1);
        System.out.println("res2 = " + res2);
        System.out.println("res3 = " + res3);
        System.out.println("res4 = " + res4);
        System.out.println("res5 = " + res5);
        System.out.println("res6 = " + res6);

    }
}
