package com.example.demo.es.events;

public class UserCreatedEvent extends Event {


    private final String userId;


    public UserCreatedEvent(String userId) {
        this.userId = userId;

    }

    public String getUserId() {
        return userId;
    }


}

