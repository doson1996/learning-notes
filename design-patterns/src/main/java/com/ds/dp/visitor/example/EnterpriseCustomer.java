package com.ds.dp.visitor.example;

/**
 * @Author ds
 * @Date 2021/4/14 11:38
 * @Description 企业客户
 */
public class EnterpriseCustomer extends Customer {

    private String linkman;

    private String linkTelephone;

    private String regAddress;

    @Override
    public void accept(Visitor visitor) {
        visitor.visitEnterpriseCustomer(this);
    }

    @Override
    protected void serviceRequest() {
        System.out.println(this.getName() + "企业提出服务请求");
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public String getLinkTelephone() {
        return linkTelephone;
    }

    public void setLinkTelephone(String linkTelephone) {
        this.linkTelephone = linkTelephone;
    }

    public String getRegAddress() {
        return regAddress;
    }

    public void setRegAddress(String regAddress) {
        this.regAddress = regAddress;
    }
}
