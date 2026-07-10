package com.ds.word;

import java.nio.file.Path;
import java.nio.file.Paths;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.document.parser.apache.poi.ApachePoiDocumentParser;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.response.ChatResponse;

/**
 * @author ds
 * @date 2026/7/10
 * @description
 */
public class WordAnalysisService {

    private final ChatModel chatModel;

    public WordAnalysisService(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    /**
     * 读取 Word 文档并让 AI 进行分析
     * @param filePath Word 文档的路径
     * @param userPrompt 用户对 AI 的指令，例如 "请总结这份文档的主要内容"
     * @return AI 的回复内容
     */
    public String analyzeWordDocument(String filePath, String userPrompt) {
        // 2. 加载并解析 Word 文档
        Path path = Paths.get(filePath);
        ApachePoiDocumentParser parser = new ApachePoiDocumentParser(); // 同时支持 .doc 和 .docx[reference:4][reference:5]
        Document document = FileSystemDocumentLoader.loadDocument(path, parser);

        // 3. 提取文档的纯文本内容
        String documentContent = document.text();
        System.out.println("文档内容长度: " + documentContent.length() + " 字符");

        // 4. 构建发送给 AI 的消息
        // 将文档内容和用户指令组合成一条完整的 UserMessage
        String fullPrompt = userPrompt + "\n\n文档内容如下：\n" + documentContent;
        UserMessage userMessage = UserMessage.from(fullPrompt);

        // 5. 调用大模型
        ChatResponse response = chatModel.chat(userMessage);
        AiMessage aiMessage = response.aiMessage();

        // 6. 返回 AI 的回复
        return aiMessage.text();
    }

}
