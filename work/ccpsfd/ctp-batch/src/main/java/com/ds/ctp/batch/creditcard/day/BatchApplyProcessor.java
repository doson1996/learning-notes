package com.ds.ctp.batch.creditcard.day;

import com.ds.at.access.AdjustLimit;
import com.ds.lib.annotation.Process;
import com.ds.lib.batch.BaseProcessJob;
import com.ds.lib.batch.ProcessResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ds
 * @date 2023/4/13
 * @description 调用联机服务批量测试
 */
@Slf4j
@Process("ccpsfd_d_batch_apply_process")
public class BatchApplyProcessor extends BaseProcessJob {

    @DubboReference(version = "1.0")
    private AdjustLimit adjustLimit;

    @Override
    protected ProcessResult mainProcess() {
        Map<String, Object> input = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            input.clear();
            input.put(String.valueOf(i), i);
            Map<String, Object> result = adjustLimit.apply(input);
            log.info("apply result = {}", result);
        }
        return ProcessResult.success();
    }

}
