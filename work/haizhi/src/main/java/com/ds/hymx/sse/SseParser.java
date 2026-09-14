package com.ds.hymx.sse;

/**
 * @author ds
 * @date 2026/9/14
 * @description
 */
public class SseParser {

    private final StringBuilder dataBuffer = new StringBuilder();

    private String eventType = null;

    private String lastEventId = null;

    private boolean hasData = false;

    /**
     * 喂入一行（不含换行符），若该行触发事件结束则返回事件，否则返回 null。
     */
    public SseEvent feed(String line) {
        // 空行 = 事件结束
        if (line == null || line.isEmpty()) {
            if (hasData) {
                SseEvent event = new SseEvent(
                        lastEventId,
                        eventType == null ? "message" : eventType,
                        dataBuffer.toString()
                );
                // 重置 data 相关字段，但保留 lastEventId（用于断线重连）
                dataBuffer.setLength(0);
                eventType = null;
                hasData = false;
                return event;
            }
            // 没有 data 的空行，忽略（比如纯注释/心跳后的空行）
            return null;
        }

        // 以冒号开头的整行是注释，忽略
        if (line.startsWith(":")) {
            return null;
        }

        // 解析字段名和值
        int colonIdx = line.indexOf(':');
        String field;
        String value;
        if (colonIdx < 0) {
            // 只有字段名，没有冒号
            field = line;
            value = "";
        } else {
            field = line.substring(0, colonIdx);
            value = line.substring(colonIdx + 1);
            // 冒号后如果有一个空格，去掉它（规范要求）
            if (value.startsWith(" ")) {
                value = value.substring(1);
            }
        }

        switch (field) {
            case "data":
                dataBuffer.append(value).append('\n');
                hasData = true;
                break;
            case "event":
                eventType = value;
                break;
            case "id":
                // 规范：id 为空字符串时不清空 lastEventId
                if (!value.isEmpty()) {
                    lastEventId = value;
                }
                break;
            case "retry":
                // 本示例暂不处理重连间隔，业务层可自行解析
                break;
            default:
                // 未知字段忽略
                break;
        }
        return null;
    }

    /**
     * 取出当前缓存的 lastEventId，用于重连时发 Last-Event-ID
     */
    public String getLastEventId() {
        return lastEventId;
    }

}
