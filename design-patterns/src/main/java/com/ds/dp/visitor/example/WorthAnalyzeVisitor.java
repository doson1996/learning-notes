package com.ds.dp.visitor.example;

/**
 * @Author ds
 * @Date 2021/4/14 15:49
 * @Description 添加功能，实现客户价值分析
 */
public class WorthAnalyzeVisitor implements Visitor {

    @Override
    public void visitEnterpriseCustomer(EnterpriseCustomer enterpriseCustomer) {
        System.out.println("对企业客户" + enterpriseCustomer.getName() + "进行价值分析");
    }

    @Override
    public void visitPersonCustomer(PersonCustomer personCustomer) {
        System.out.println("对个人客户" + personCustomer.getName() + "进行价值分析");
    }
}
