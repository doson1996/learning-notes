package com.ds.hymx.sse;

/**
 * @author ds
 * @date 2026/9/14
 * @description
 */
public class SseEvent {

    private String id;

    private String event;

    private String data;

    public SseEvent(String id, String event, String data) {
        this.id = id;
        this.event = event;
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public String getEvent() {
        return event;
    }

    public String getData() {
        return data;
    }

    @Override
    public String toString() {
        return "SseEvent{id='" + id + "', event='" + event + "', data='" + data + "'}";
    }
}
