package com.ds.at.access.controller;

import com.ds.at.access.AdjustLimit;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author ds
 * @date 2023/4/10
 * @description
 */

@RestController
@AllArgsConstructor
@RequestMapping("adjust")
public class AdjustController {

    private AdjustLimit adjustLimit;

    @PostMapping("apply")
    public Map<String, Object> apply(@RequestBody Map<String, Object> input) {
        Map<String, Object> res = adjustLimit.apply(input);
        return res;
    }

}
