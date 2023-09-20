package com.example.demo.aggregate;


import java.io.Serializable;

public class UserModel implements Serializable {
    private static final long serialVersionUID = 802539347880595450L;
    private Long userId;
    private Integer totalPoints;


    public UserModel() {
    }

    public UserModel(Long userId, Integer totalPoints) {
        this.userId = userId;
        this.totalPoints = totalPoints;
    }

    public void handleUserCreationEvent(Long userId){
        this.userId = userId;
        this.totalPoints = 0;
    }

    public void handleAddPointsEvent(Integer points){
        this.totalPoints += points;
    }

    public void handleRemovePointsEvent(Integer points){
        this.totalPoints -= points;

    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(Integer totalPoints) {
        this.totalPoints = totalPoints;
    }
}
