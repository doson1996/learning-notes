package com.ds.lmstudio;

/**
 * @author ds
 * @date 2026/7/10
 * @description
 */
public class LmStudioDemo {
    public static void main(String[] args) {
        LmStudioChatModel chatModel = LmStudioChatModel.builder()
                .baseUrl("http://localhost:1234/api/v1/chat")
                .apiKey("sk-lm-QVicfpKL:thgcqqmOEn0deXlAk5U9")
                .modelName("liquid/lfm2.5-1.2b")
                .build();

        String result = chatModel.chat("你是谁");
        System.out.println("result = " + result);
    }
}
