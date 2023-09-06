package com.example.demo.es.events;

public class DecreasedUserPointsEvent extends Event{
    private final String userId;
    private final Integer points;

    public DecreasedUserPointsEvent(String userId, Integer points) {
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
