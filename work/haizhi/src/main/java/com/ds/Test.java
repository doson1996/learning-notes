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
        String q = "请描述重庆银行基本情况!";
        q = "重庆银行的主要业务范围是什么？";
      //  System.out.println(HanLP.convertToPinyinString(q, "", false));
        System.out.println(HanLP.segment(q));
        System.out.println(StandardTokenizer.segment(q));
        System.out.println(HanLP.newSegment("感知机").seg(q));
    }
}
