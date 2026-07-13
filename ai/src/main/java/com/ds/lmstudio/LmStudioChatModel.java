package com.ds.lmstudio;

import java.util.List;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.Content;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.internal.Utils;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.request.ChatRequest;
import dev.langchain4j.model.chat.response.ChatResponse;

/**
 * @author ds
 * @date 2026/7/10
 * @description
 */
public class LmStudioChatModel implements ChatModel {

    private final String apiKey;

    private final String baseUrl;

    private final String modelName;

    protected LmStudioChatModel(String baseUrl, String apiKey, String modelName) {
        if (Utils.isNullOrBlank(apiKey)) {
            throw new IllegalArgumentException("LmStudio api key must be defined.");
        } else {
            this.apiKey = apiKey;
            this.baseUrl = baseUrl;
            this.modelName = modelName;
        }
    }

    @Override
    public ChatResponse doChat(ChatRequest chatRequest) {
        StringBuilder input = new StringBuilder();
        List<ChatMessage> messageList = chatRequest.messages().stream().toList();

        for (ChatMessage chatMessage : messageList) {
            if (chatMessage instanceof UserMessage userMessage) {
                for (Content content : userMessage.contents()) {
                    input.append(content).append("\n");
                }
            }
        }

        JSONObject body = new JSONObject();
        body.put("model", modelName);
        body.put("input", input.toString());
        body.put("system_prompt", "你是一个word内容解析、提取专家");

        HttpRequest httpRequest = HttpUtil.createPost(baseUrl).bearerAuth(apiKey).body(body.toString());
        HttpResponse httpResponse = httpRequest.execute();
        String resultStr = httpResponse.body();
        httpResponse.close();

        JSONObject resultJson = JSONObject.parseObject(resultStr);
        JSONArray outputArr = resultJson.getJSONArray("output");
        if (outputArr != null && !outputArr.isEmpty()) {
            for (int i = 0; i < outputArr.size(); i++) {
                JSONObject output = outputArr.getJSONObject(i);
                if (output != null && !output.isEmpty()) {
                    String type = output.getString("type");
                    String text = "";
                    String thinking = "";
                    if ("message".equals(type)) {
                        text = output.getString("content");
                    }

                    if ("reasoning".equals(type)) {
                        thinking = output.getString("content");
                    }
                    AiMessage aiMessage = AiMessage.builder()
                            .thinking(thinking)
                            .text(text)
                            .build();
                    return ChatResponse.builder()
                            .modelName(modelName)
                            .aiMessage(aiMessage).build();
                }
            }
        }

        AiMessage aiMessage = AiMessage.builder()
                .text("系统繁忙!")
                .build();
        return ChatResponse.builder()
                .modelName(modelName)
                .aiMessage(aiMessage)
                .build();
    }

    public static LmStudioChatModel.LmStudioChatModelBuilder builder() {
        return new LmStudioChatModel.LmStudioChatModelBuilder();
    }

    public static class LmStudioChatModelBuilder {

        private String baseUrl;

        private String apiKey;

        private String modelName;

        public LmStudioChatModelBuilder() {
        }

        public LmStudioChatModel.LmStudioChatModelBuilder baseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        public LmStudioChatModel.LmStudioChatModelBuilder apiKey(String apiKey) {
            this.apiKey = apiKey;
            return this;
        }

        public LmStudioChatModel.LmStudioChatModelBuilder modelName(String modelName) {
            this.modelName = modelName;
            return this;
        }

        public LmStudioChatModel build() {
            return new LmStudioChatModel(this.baseUrl, this.apiKey, this.modelName);
        }
    }

}