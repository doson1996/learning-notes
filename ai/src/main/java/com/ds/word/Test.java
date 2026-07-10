package com.ds.word;

import com.ds.lmstudio.LmStudioChatModel;
import dev.langchain4j.community.model.dashscope.QwenChatModel;
import dev.langchain4j.model.chat.ChatModel;

/**
 * @author ds
 * @date 2026/7/10
 * @description
 */
public class Test {
    public static void main(String[] args) {
        ChatModel chatModel = qwenChatModel();
        WordAnalysisService service = new WordAnalysisService(chatModel);

        String result = service.analyzeWordDocument(
                "D:\\haizhi\\doc\\2025\\03.尽调\\数字分析助手\\报告模板\\重庆银行小微公司授信业务调查报告.docx",
                "抓取文档中的注册资金并展示出来"
        );

        System.out.println("=== AI 分析结果 ===");
        System.out.println(result);
    }

    /**
     * 千问
     *
     * @return
     */
    private static ChatModel qwenChatModel() {
        // 1. 初始化 QwenChatModel
        // 建议将 apiKey 配置在 application.yml 中，而不是硬编码
        return QwenChatModel.builder()
                .apiKey(System.getProperty("AI_API_KEY")) // 替换为你的 API Key
                .modelName("qwen3-max-2025-09-23")           // 可选: qwen-turbo, qwen-plus, qwen-max
                .temperature(0.7f)                // 控制随机性
                .maxTokens(2048)                  // 控制输出长度
                .build();
    }

    /**
     * LmStudio
     *
     * @return
     */
    private static ChatModel LmStudioChatModel() {
        return LmStudioChatModel.builder()
                .baseUrl("http://localhost:1234/api/v1/chat")
                .apiKey("sk-lm-QVicfpKL:thgcqqmOEn0deXlAk5U9")
                .modelName("deepseek/deepseek-r1-0528-qwen3-8b")
                .build();
    }

}
