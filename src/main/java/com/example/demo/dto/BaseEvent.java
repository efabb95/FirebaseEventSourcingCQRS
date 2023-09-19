package com.example.demo.dto;

import com.google.cloud.firestore.annotation.DocumentId;
import com.google.cloud.spring.data.firestore.Document;

import java.util.Date;

@Document(collectionName = "eventStore")
public class BaseEvent {
    @DocumentId
    private String eventId;
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
