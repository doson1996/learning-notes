package com.ds.dp.visitor.example;

/**
 * @Author ds
 * @Date 2021/4/14 15:43
 * @Description
 */
public class ServiceVisitor implements Visitor {
    @Override
    public void visitEnterpriseCustomer(EnterpriseCustomer enterpriseCustomer) {
        System.out.println(enterpriseCustomer.getName() + "提出企业服务");
    }

    @Override
    public void visitPersonCustomer(PersonCustomer personCustomer) {
        System.out.println(personCustomer.getName() + "提出个人服务");
    }
}
