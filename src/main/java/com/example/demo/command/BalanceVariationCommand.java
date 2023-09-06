package com.example.demo.command;

public class BalanceVariationCommand {
    private final String userId;
    private final Integer points;
    private final String variationStatus;

    public BalanceVariationCommand(String userId, Integer points, String variationStatus) {
        this.userId = userId;
        this.points = points;
        this.variationStatus = variationStatus;
    }

    public String getUserId() {
        return userId;
    }

    public Integer getPoints() {
        return points;
    }

    public String getVariationStatus() {
        return variationStatus;
    }
}
