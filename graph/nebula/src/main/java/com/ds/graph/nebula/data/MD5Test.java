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
//        company = "915000002024110503疑似企业1";
//        company = "915000002024110504疑似企业2";
        company = "915000002025011401受托支付1";
        company = "915000002025011402受托支付2";
        company = "915000002025011403受托支付3";
        company = "915000002025011403受托支付4";

        String s = SecureUtil.md5(company);
        System.out.println("s = " + s.toUpperCase());
    }
}
