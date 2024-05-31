package com.ds;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.tokenizer.StandardTokenizer;

/**
 * @author ds
 * @date 2024/5/10
 * @description
 */
public class Test {
    public static void main(String[] args) {
        HanLP.segment("");
        String q = "重庆测试科技有限责任公司基本情况？";


        System.out.println();
      //  System.out.println(HanLP.convertToPinyinString(q, "", false));
        System.out.println(HanLP.newSegment().enableOrganizationRecognize(true).seg(q));
        System.out.println(StandardTokenizer.segment(q));
        System.out.println(HanLP.newSegment("感知机").seg(q));
    }
}
