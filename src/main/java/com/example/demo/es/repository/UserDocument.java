package com.example.demo.es.repository;


import com.google.cloud.firestore.annotation.DocumentId;
import com.google.cloud.spring.data.firestore.Document;

import java.util.Date;

@Document(collectionName = "users")
public class UserDocument {

    @DocumentId private String UUID;

    private String id;

    private Integer points;

    private final Date created = new Date();

    public UserDocument() {
        super();
    }

    public UserDocument(String id, Integer points) {
        this.id = id;
        this.points = points;
    }


    public String getUUID() {
        return UUID;
    }
    public String getUserId() {
        return id;
    }

    public Integer getPoints() {
        return points;
    }

    public Date getCreated() {
        return created;
    }
}