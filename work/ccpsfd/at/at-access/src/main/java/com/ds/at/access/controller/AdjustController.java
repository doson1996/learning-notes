package com.ds.at.access.controller;

import com.ds.at.access.AdjustLimit;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author ds
 * @date 2023/4/10
 * @description
 */

@RestController
//@AllArgsConstructor
@RequestMapping("adjust")
public class AdjustController {

    @Resource
    @Qualifier("adjustLimit")
    private AdjustLimit adjustLimit;

    @Resource
    @Qualifier("adjustLimitV1")
    private AdjustLimit adjustLimitV1;

    @PostMapping("apply")
    public Map<String, Object> apply(@RequestBody Map<String, Object> input) {
        Map<String, Object> res = adjustLimit.apply(input);
        return res;
    }

    @PostMapping("apply1")
    public Map<String, Object> apply1(@RequestBody Map<String, Object> input) {
        Map<String, Object> res = adjustLimitV1.apply(input);
        return res;
    }

}
