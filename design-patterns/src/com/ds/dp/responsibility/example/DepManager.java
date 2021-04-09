package com.ds.dp.responsibility.example;

/**
 * @Author ds
 * @Date 2021/4/9 10:38
 * @Description
 */
public class DepManager extends Handler{

    String result = "";

    @Override
    public String handlerFeeRequest(String user, double fee) {
        //小于1000部门经理处理
        if (fee < 1000){
            if ("张三".equals(user)) {
                result = "部门经理同意" + user + "申请费用" + fee + "元";
            } else {
                result = "部门经理不同意" + user + "申请费用" + fee + "元";
            }

            return result;
        } else {
            //超过1000，继续传递给级别更高的人
            if (handler != null) {
                result = handler.handlerFeeRequest(user, fee);
            }
        }

        return result;
    }
}
