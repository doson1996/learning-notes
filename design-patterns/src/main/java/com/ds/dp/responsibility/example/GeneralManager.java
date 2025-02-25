package com.ds.dp.responsibility.example;

/**
 * @Author ds
 * @Date 2021/4/9 10:38
 * @Description
 */
public class GeneralManager extends Handler {

    String result = "";

    @Override
    public String handlerFeeRequest(String user, double fee) {
        //总经理后面都可以处理
        if (fee >= 1000) {
            if ("张三".equals(user)) {
                result = "总经理同意" + user + "申请费用" + fee + "元";
            } else {
                result = "总经理不同意" + user + "申请费用" + fee + "元";
            }

            return result;
        } else {
            //如果有后继对象，继续传递
            if (handler != null) {
                result = handler.handlerFeeRequest(user, fee);
            }
        }

        return result;
    }
}
