package com.ds.ctp.batch.creditcard.night;

import com.ds.ctp.batch.service.SyncIsasService;
import com.ds.lib.annotation.Process;
import com.ds.lib.batch.BaseProcessJob;
import com.ds.lib.batch.ProcessResult;

import java.util.ArrayList;

/**
 * @author ds
 * @date 2023/9/19
 * @description 同步门户数据
 */
@Process(jobName = "ccpsfd_n_sync_isas_process")
public class SyncIsasProcessor extends BaseProcessJob {
    @Override
    protected ProcessResult mainProcess() {
        SyncIsasService syncIsasService = new SyncIsasService();
        syncIsasService.execute(new ArrayList<>());
        return ProcessResult.success();
    }
}
