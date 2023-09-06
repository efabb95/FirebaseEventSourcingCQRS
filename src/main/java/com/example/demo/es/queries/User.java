package com.example.demo.es.queries;

import com.google.cloud.firestore.annotation.DocumentId;

public class User {

    private final String userId;
    private final Integer points;

    public User(String userId, Integer points) {
        this.userId = userId;
        this.points = points;
    }

    public String getUserId() {
        return userId;
    }


    public Integer getPoints() {
        return points;
    }
}
