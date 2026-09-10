package com.ds.hymx;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import cn.hutool.core.util.IdUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;
import okhttp3.sse.EventSources;

/**
 * 行业模型
 *
 * @author ds
 * @date 2026/9/9
 * @description
 */
public class HymxDemo {
    public static void main(String[] args) {
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
            // 1. 创建 OkHttpClient，关键：将 readTimeout 设为 0（永不超时）[reference:5]
            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(0, TimeUnit.MILLISECONDS)  // 长连接关键设置
                    .build();

            // 2. 创建 EventSource 工厂
            EventSource.Factory factory = EventSources.createFactory(client);

            // 3. 构建请求（以 POST 为例，GET 同理）
            MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
            String jsonBody = "{\"prompt\":\"你好\"}";
            RequestBody requestBody = RequestBody.create(jsonBody, mediaType);

            Request request = new Request.Builder()
                    .url("https://router.fis.aliyuncs.com/finx/api/agent/run/stream?requestId=" + requestId)
                    .get()
                    .addHeader("Accept", "text/event-stream")
                    .addHeader("Authorization", "Bearer " + token)
                    .addHeader("X-Principal-Id", principalId)
                    .build();

            StringBuilder sb = new StringBuilder();

            // 4. 自定义监听器处理事件
            EventSourceListener listener = new EventSourceListener() {
                @Override
                public void onOpen(EventSource eventSource, Response response) {
                    System.out.println("SSE 连接已建立");
                }

                @Override
                public void onEvent(EventSource eventSource, String id, String type, String data) {
                    // 核心方法：每收到一个完整事件就会回调[reference:7]
                    System.out.println("收到数据: " + data);
                    // 在这里处理你的业务逻辑
                    try {
                        JSONObject dataJson = JSON.parseObject(data);
                        String eventType = dataJson.getString("eventType");
                        if ("TEXT_START".equals(eventType) || "TEXT_DELTA".equals(eventType)) {
                            JSONObject streamData = dataJson.getJSONObject("data");
                            if (streamData != null) {
                                String streamDataType = streamData.getString("type");
                                String streamDataText = streamData.getString("text");
                                if ("text".equals(streamDataType)) {
                                    sb.append(streamDataText);
                                }
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("接收数据处理异常：" + e);
                    }
                }

                @Override
                public void onClosed(EventSource eventSource) {
                    System.out.println("SSE 连接已关闭");
                    countDownLatch.countDown();
                }

                @Override
                public void onFailure(EventSource eventSource, Throwable t, Response response) {
                    System.err.println("发生错误: " + t.getMessage());
                }
            };

            // 5. 创建并启动 EventSource
            EventSource eventSource = factory.newEventSource(request, listener);

            // 6. 保持主线程运行（实际项目中根据情况管理）
            System.out.println("程序等待...");
            countDownLatch.await(60, TimeUnit.SECONDS);
            eventSource.cancel();
            System.out.println("运行结束...");

            System.out.println("结果：" + sb);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
