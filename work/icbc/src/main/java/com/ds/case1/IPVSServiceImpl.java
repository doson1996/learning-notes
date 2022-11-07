package com.ds.case1;

import java.util.ArrayList;
import java.util.List;

/**
 * 模拟业务类
 *
 * @author ds
 */
public class IPVSServiceImpl {

    /**
     * 模拟业务执行方法
     *
     * @param count 模拟从数据库查询到的消息条数
     */
    public static void exec(int count) {

        List<Integer> succIds = new ArrayList<>();

        // 模拟db查数后遍历
        for (int i = 0; i < count; i++) {
            boolean send = MsgSendUtil.send("msg" + i, "topic" + (i % 10));
            if (send) {
                succIds.add(i);
            }
        }

        batchUpdate(succIds);
    }

    /**
     * 模拟批量更新数据库
     *
     * @param succIds
     */
    private static void batchUpdate(List<Integer> succIds) {
        for (Integer succId : succIds) {
            // update
        }
    }
}
