package com.ds.dp.visitor.example;

import com.ds.dp.visitor.simple.ElementA;
import com.ds.dp.visitor.simple.ElementB;

/**
 * @Author  ds
 * @Date    2021/4/14 13:50
 * @Description
 */public interface Visitor {

    /**
     * 访问enterpriseCustomer
     * @param enterpriseCustomer
     */
     void visitEnterpriseCustomer(EnterpriseCustomer enterpriseCustomer);

    /**
     * 访问personCustomer
     * @param personCustomer
     */
    void visitPersonCustomer(PersonCustomer personCustomer);
}
