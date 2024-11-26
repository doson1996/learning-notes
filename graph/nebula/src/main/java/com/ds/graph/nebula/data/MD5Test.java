package com.ds.graph.nebula.data;

import cn.hutool.crypto.SecureUtil;

/**
 * @author ds
 * @date 2024/11/22
 * @description
 */
public class MD5Test {
    public static void main(String[] args) {
        String company = "915000002024110502重庆测试技术有限公司";
        String s = SecureUtil.md5(company);
        System.out.println("s = " + s);
    }
}
