package com.ds.dp.visitor.example;

/**
 * @Author ds
 * @Date 2021/4/14 11:47
 * @Description 使用设计模式
 */
public class Client {

    public static void main(String[] args) {
        ObjectStructure os = new ObjectStructure();

        Customer pCustomer = new PersonCustomer();
        pCustomer.setName("张三");
        // pCustomer.serviceRequest();
        os.addElement(pCustomer);

        Customer eCustomer = new EnterpriseCustomer();
        eCustomer.setName("xx集团");
        // eCustomer.serviceRequest();
        os.addElement(eCustomer);

        Visitor visitor = new ServiceVisitor();
        os.handlerRequest(visitor);

        //添加功能
        Visitor visitor1 = new WorthAnalyzeVisitor();
        os.handlerRequest(visitor1);

    }
}
