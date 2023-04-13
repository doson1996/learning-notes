package com.ds.ctp.batch.creditcard.night;

import com.ds.lib.annotation.Process;
import com.ds.lib.batch.BaseProcessJob;
import com.ds.lib.batch.ProcessResult;
import lombok.extern.slf4j.Slf4j;

/**
 * @author ds
 * @date 2023/4/13
 * @description 测试批量
 */
@Slf4j
@Process(value = "ccpsfd_n_test_process")
public class TestProcessor extends BaseProcessJob {
    @Override
    protected ProcessResult mainProcess() {
        // 业务处理
        log.info("testProcess run...");
        return ProcessResult.success();
    }

}
