package com.ds.dp.visitor.example;

/**
 * @Author ds
 * @Date 2021/4/14 11:37
 * @Description 个人客户
 */
public class PersonCustomer extends Customer {

    private String telephone;

    @Override
    public void accept(Visitor visitor) {
        visitor.visitPersonCustomer(this);
    }

    @Override
    protected void serviceRequest() {
        System.out.println("客户" + this.getName() + "提出服务请求");
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
