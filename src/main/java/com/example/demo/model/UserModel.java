package com.example.demo.model;


import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class UserModel implements Serializable {
    private static final long serialVersionUID = 802539347880595450L;
    private Long userId;
    private Integer totalPoints;
    private Date lastEventDate;

    public UserModel() {
    }

    public UserModel(Long userId, Integer totalPoints) {
        this.userId = userId;
        this.totalPoints = totalPoints;
    }

    public void handleUserCreationEvent(Long userId, Date date){
        this.userId = userId;
        this.totalPoints = 0;
        this.lastEventDate = date;
    }

    public void handleAddPointsEvent(Integer points, Date date){
        this.totalPoints += points;
        this.lastEventDate = date;
    }

    public void handleRemovePointsEvent(Integer points, Date date){
        this.totalPoints -= points;
        this.lastEventDate = date;

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

    public Date getLastEventDate() {
        return lastEventDate;
    }

    public void setLastEventDate(Date lastEventDate) {
        this.lastEventDate = lastEventDate;
    }

    public Map<String, Object> toMap(){
        Map<String, Object> fields = new HashMap<>();
        fields.put("userId", userId);
        fields.put("totalPoints", totalPoints);
        fields.put("lastEventDate", lastEventDate);
        return fields;
    }
}
