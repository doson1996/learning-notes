package com.ds;

import com.hankcs.hanlp.model.crf.CRFLexicalAnalyzer;
import com.hankcs.hanlp.seg.Dijkstra.DijkstraSegment;
import com.hankcs.hanlp.seg.NShort.NShortSegment;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.StandardTokenizer;

import java.io.IOException;
import java.util.List;

/**
 * @author ds
 * @date 2024/5/10
 * @description
 */
public class Test24051001 {
    public static void main(String[] args) throws IOException {

        String question = "重庆测试科技有限责任公司基本情况";

        List<Term> termList = StandardTokenizer.segment(question);
        System.out.println(termList);


        Segment nShortSegment = new NShortSegment().enableCustomDictionary(false).enablePlaceRecognize(true).enableOrganizationRecognize(true);
        Segment shortestSegment = new DijkstraSegment().enableCustomDictionary(false).enablePlaceRecognize(true).enableOrganizationRecognize(true);
        String[] testCase = new String[]{question};
        for (String sentence : testCase) {
            System.out.println("N-最短分词：" + nShortSegment.seg(sentence) +
                    "\n最短路分词：" + shortestSegment.seg(sentence));
        }

        // HanLP.Config.ShowTermNature = true;    // 关闭词性显示
        Segment segment = new CRFLexicalAnalyzer();//旧版本使用的是CRFSegment，已被遗弃
        String[] sentenceArray = new String[]
                {
                        question
                };
        for (String sentence : sentenceArray) {
            termList = segment.seg(sentence);
            System.out.println(termList);
        }

    }
}
