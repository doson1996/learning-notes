package com.ds.dp.responsibility.example;

/**
 * @Author ds
 * @Date 2021/4/9 10:38
 * @Description
 */
public class ProjectManager extends Handler {

    String result = "";

    @Override
    public String handlerFeeRequest(String user, double fee) {
        //小于500项目经理处理
        if (fee < 500) {
            if ("张三".equals(user)) {
                result = "项目经理同意" + user + "申请费用" + fee + "元";
            } else {
                result = "项目经理不同意" + user + "申请费用" + fee + "元";
            }

            return result;
        } else {
            //超过500，继续传递给级别更高的人
            if (handler != null) {
                result = handler.handlerFeeRequest(user, fee);
            }
        }

        return result;
    }
}
