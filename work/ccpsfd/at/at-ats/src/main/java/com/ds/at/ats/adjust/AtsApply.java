package com.ds.at.ats.adjust;

import com.ds.lib.base.AbstractBaseService;
import com.ds.lib.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author ds
 * @date 2023/4/12
 * @description
 */
@Slf4j
@Service("apply")
public class AtsApply extends AbstractBaseService {

    @Override
    protected Map<String, Object> execute(Map<String, Object> input) {
        log.info("atsApply.execute..., input: {}", input);
        Map<String, Object> result = Result.success();
        result.put("data", input);
        return result;
    }

}
