package com.ds.basic.dynamicproxy.spring.d6tx;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ds
 * @date 2026/2/2
 * @description
 */
@Service
public class TxService {

    @Transactional
    public void tx() {
        System.out.println("tx");
    }

}
