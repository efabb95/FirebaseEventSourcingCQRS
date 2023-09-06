package com.example.demo.command;

public class CreateUserCommand {
    private final String userId;

    public CreateUserCommand(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

}
