package com.example.demo.dto.event;

import java.util.Date;
import java.util.UUID;

public class BaseEvent {

    private String eventId = UUID.randomUUID().toString();
    private Long userId;
    private Date date = new Date();
    private String eventType;
    private Object eventPayload;

    public BaseEvent() {
    }

    public BaseEvent(Long userId, String eventType, Object eventPayload) {
        this.userId = userId;
        this.eventType = eventType;
        this.eventPayload = eventPayload;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Object getEventPayload() {
        return eventPayload;
    }

    public void setEventPayload(Object eventPayload) {
        this.eventPayload = eventPayload;
    }
}
