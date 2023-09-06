package com.example.demo.es.events;

import com.google.cloud.firestore.annotation.DocumentId;

import java.util.Date;

public class Event {

    @DocumentId
    private String UUID;
    private final Date created = new Date();


    public String getUUID() {
        return UUID;
    }

    public Date getCreated() {
        return created;
    }
}
