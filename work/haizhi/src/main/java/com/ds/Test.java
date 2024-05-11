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
        String q = "请描述重庆银行基本情况!";
        q = "重庆银行的主要业务范围是什么？";
        q = "讲一下中国工商银行股份有限公司的基本情况？";
        q = "重庆农村商业银行股份有限公司的基本情况？";
        q = "讲一下万科企业股份有限公司的基本情况？";
       // q = "讲一下重庆中烟工业有限责任公司基本情况？";
        q = "重庆测试科技有限责任公司基本情况？";


        System.out.println();
      //  System.out.println(HanLP.convertToPinyinString(q, "", false));
        System.out.println(HanLP.newSegment().enableOrganizationRecognize(true).seg(q));
        System.out.println(StandardTokenizer.segment(q));
        System.out.println(HanLP.newSegment("感知机").seg(q));
    }
}
