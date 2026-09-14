package com.ds.hymx.sse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 基于 JDK 8 HttpURLConnection 的 SSE 客户端。
 * 支持：自定义 Header、自动重连、Last-Event-ID、心跳注释忽略、多行 data 拼接。
 */
public class SseClient {

    private final String url;
    private final SseListener listener;
    private final long reconnectDelayMs;
    /** 用户自定义 header（不可变拷贝） */
    private final Map<String, String> headers;

    private final AtomicBoolean running = new AtomicBoolean(false);
    private Thread workerThread;
    private volatile HttpURLConnection currentConn;

    /** 用于重连时发送 Last-Event-ID */
    private volatile String lastEventId;

    // ========== 构造方法（多重重载，向后兼容） ==========

    public SseClient(String url, SseListener listener) {
        this(url, listener, Collections.<String, String>emptyMap(), 3000L);
    }

    public SseClient(String url, SseListener listener, Map<String, String> headers) {
        this(url, listener, headers, 3000L);
    }

    public SseClient(String url, SseListener listener,
                     Map<String, String> headers, long reconnectDelayMs) {
        this.url = url;
        this.listener = listener;
        this.reconnectDelayMs = reconnectDelayMs;
        // 拷贝一份，避免外部修改；LinkedHashMap 保留设置顺序
        this.headers = headers == null
                ? Collections.<String, String>emptyMap()
                : Collections.unmodifiableMap(new LinkedHashMap<>(headers));
    }

    // ========== 生命周期 ==========

    public void start() {
        if (!running.compareAndSet(false, true)) {
            return;
        }
        workerThread = new Thread(this::runLoop, "sse-client-" + url);
        workerThread.setDaemon(true);
        workerThread.start();
    }

    public void stop() {
        if (!running.compareAndSet(true, false)) {
            return;
        }
        HttpURLConnection conn = currentConn;
        if (conn != null) {
            try { conn.disconnect(); } catch (Exception ignored) { }
        }
        if (workerThread != null) {
            workerThread.interrupt();
        }
    }

    // ========== 主循环 ==========

    private void runLoop() {
        while (running.get()) {
            try {
                connectAndRead();
                if (running.get()) {
                    listener.onClosed();
                }
            } catch (Throwable t) {
                if (!running.get()) {
                    return;
                }
                listener.onError(t);
            }

            if (running.get()) {
                try {
                    Thread.sleep(reconnectDelayMs);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }
    }

    // ========== 单次连接与读取 ==========

    private void connectAndRead() throws IOException {
        HttpURLConnection conn = null;
        try {
            URL u = new URL(url);
            conn = (HttpURLConnection) u.openConnection();
            currentConn = conn;

            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.setUseCaches(false);
            // 关键：读超时 0 = 永不超时
            conn.setReadTimeout(0);
            conn.setConnectTimeout(10_000);

            // 应用所有 header（含用户自定义）
            applyHeaders(conn);

            int code = conn.getResponseCode();
            if (code != 200) {
                drainQuietly(conn.getErrorStream());
                throw new IOException("SSE 服务端返回非 200: " + code);
            }

            String contentType = conn.getContentType();
            if (contentType != null
                    && !contentType.toLowerCase().contains("text/event-stream")) {
                System.err.println("[SSE] 警告：Content-Type 非 text/event-stream: " + contentType);
            }

            listener.onOpen();

            InputStream in = conn.getInputStream();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(in, StandardCharsets.UTF_8));

            SseParser parser = new SseParser();
            String line;
            while (running.get() && (line = reader.readLine()) != null) {
                SseEvent event = parser.feed(line);
                if (event != null) {
                    if (event.getId() != null) {
                        lastEventId = event.getId();
                    }
                    listener.onEvent(event);
                }
            }
        } finally {
            currentConn = null;
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

    // ========== Header 应用逻辑 ==========

    /**
     * 应用请求头，优先级从低到高：
     *   1) 默认基础 header（Accept / Cache-Control）
     *   2) Last-Event-ID（断线重连自动携带）
     *   3) 用户自定义 header（可覆盖上面任意一项）
     */
    private void applyHeaders(HttpURLConnection conn) {
        // 1. 默认基础 header
        conn.setRequestProperty("Accept", "text/event-stream");
        conn.setRequestProperty("Cache-Control", "no-cache");

        // 2. Last-Event-ID
        if (lastEventId != null && !lastEventId.isEmpty()) {
            conn.setRequestProperty("Last-Event-ID", lastEventId);
        }

        // 3. 用户自定义 header（最高优先级）
        for (Map.Entry<String, String> e : headers.entrySet()) {
            String name = e.getKey();
            String value = e.getValue();
            if (name == null || name.isEmpty()) {
                continue;
            }
            try {
                conn.setRequestProperty(name, value);
            } catch (IllegalArgumentException ex) {
                // JDK 内部对部分 header 做了限制（如 Host、Connection 等），跳过
                System.err.println("[SSE] 忽略受限 header: " + name);
            }
        }
    }

    private void drainQuietly(InputStream is) {
        if (is == null) return;
        try (InputStream in = is) {
            byte[] buf = new byte[1024];
            while (in.read(buf) != -1) { /* discard */ }
        } catch (IOException ignored) { }
    }
}