package com.ds.lib.batch;

import org.springframework.context.ApplicationContext;

/**
 * @author ds
 * @date 2023/4/13
 * @description 批量启动器
 */
public class BatchBootUp {

    public void bootUp(ApplicationContext context, String[] args) {
        String jobName = args[0];
        BaseProcessJob job = context.getBean(jobName, BaseProcessJob.class);
        job.run();
    }

}
