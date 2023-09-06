package com.example.demo.es.events;

public class IncreasedUserPointsEvent  extends Event{

    private final String userId;
    private final Integer points;

    public IncreasedUserPointsEvent(String userId, Integer points) {
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
