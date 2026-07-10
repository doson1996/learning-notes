package com.ds;

import dev.langchain4j.community.model.dashscope.QwenChatModel;

/**
 * @author ds
 * @date 2026/7/10
 * @description
 */
public class DashScopeDemo {
    public static void main(String[] args) {
        try {
            QwenChatModel chatModel = QwenChatModel.builder()
                    .apiKey(System.getProperty("AI_API_KEY"))
                    .modelName("qwen-plus-1220")
                    .build();

            String result = chatModel.chat("你是谁");
            System.out.println("result = " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
