package com.ds.hymx.sse;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import cn.hutool.core.util.IdUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;

/**
 * @author ds
 * @date 2026/9/14
 * @description
 */
public class Demo {
    public static void main(String[] args) throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        String url = "https://router.fis.aliyuncs.com/finx/api/agent/run/async";
        String token = System.getProperty("token");
        String principalId = System.getProperty("principalId");
        String agentId = System.getProperty("agentId");

        HttpRequest post = HttpUtil.createPost(url);
        post.bearerAuth(token);
        post.header("X-Principal-Id", principalId);
        JSONObject body = new JSONObject();
        body.put("agentId", agentId);
        body.put("userInput", "汽车制造业分析");
        body.put("sessionId", "1");
        body.put("bizKey", IdUtil.fastSimpleUUID());
        post.body(body.toJSONString());
        try (HttpResponse execute = post.execute()) {
            String resultStr = execute.body();
            System.out.println("resultStr = " + resultStr);
            JSONObject resultStrJson = JSON.parseObject(resultStr);
            String requestId = resultStrJson.getJSONObject("data").getString("requestId");

            Map<String, String> headers = new HashMap<>();
            headers.put("Authorization", "Bearer " + token);
            headers.put("X-Principal-Id", principalId);

            StringBuilder sb = new StringBuilder();
            SseClient sseClient = new SseClient(
                    "https://router.fis.aliyuncs.com/finx/api/agent/run/stream?requestId=" + requestId,
                    new SseListener() {
                        @Override
                        public void onOpen() {
                            System.out.println("[SSE] 连接已建立");
                        }

                        @Override
                        public void onEvent(SseEvent event) {
                            System.out.println("[SSE] event=" + event.getEvent()
                                    + ", id=" + event.getId()
                                    + ", data=" + event.getData());
                            // TODO: 你的业务处理
                            String eventType = event.getEvent();
                            String eventData = event.getData();
                            if (("TEXT_START".equals(eventType) || "TEXT_DELTA".equals(eventType))
                                    && eventData != null && !eventData.isEmpty()) {
                                JSONObject eventDataJson = JSONObject.parseObject(eventData);
                                if (eventDataJson != null && eventDataJson.containsKey("data")) {
                                    JSONObject data = eventDataJson.getJSONObject("data");
                                    if (data != null && !data.isEmpty()) {
                                        String streamDataType = data.getString("type");
                                        String streamDataText = data.getString("text");
                                        if ("text".equals(streamDataType)) {
                                            sb.append(streamDataText);
                                        }
                                    }
                                }
                            }
                        }

                        @Override
                        public void onClosed() {
                            System.out.println("[SSE] 连接已关闭");
                            countDownLatch.countDown();
                        }

                        @Override
                        public void onError(Throwable t) {
                            System.err.println("[SSE] 发生错误: " + t.getMessage());
                        }
                    },
                    headers,
                    3000L // 重连间隔 3 秒
            );

            sseClient.start();

            // 模拟运行 60 秒后主动关闭
            countDownLatch.await();
            sseClient.stop();
            System.out.println("结果：" + sb);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
