package com.ds.dp.responsibility.icbc;

import java.util.Map;

/**
 * 开始步骤
 *
 * @author ds
 */
public class StartStep extends NoneStep {

    @Override
    public void execute(Map<String, Object> context) throws Exception {
        setNextStep(new DataCheckStep());
    }
}
