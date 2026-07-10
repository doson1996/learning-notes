package com.ds;

import dev.langchain4j.model.openai.OpenAiChatModel;

/**
 * @author ds
 * @date 2026/7/8
 * @description
 */
public class Demo01 {
    public static void main(String[] args) {
        OpenAiChatModel openAiChatModel = OpenAiChatModel.builder()
                .baseUrl("http://localhost:1234/v1/completions")
                .apiKey("sk-lm-QVicfpKL:thgcqqmOEn0deXlAk5U9")
                .modelName("liquid/lfm2.5-1.2b")
                .build();

        String result = openAiChatModel.chat("你是谁");
        System.out.println("result = " + result);
    }
}
