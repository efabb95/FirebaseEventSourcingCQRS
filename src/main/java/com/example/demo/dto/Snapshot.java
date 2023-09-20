package com.example.demo.dto;

import com.example.demo.aggregate.UserModel;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Snapshot extends UserModel {
    private static final long serialVersionUID = -2443178174412078857L;
    private Date lastEventDate;

    public Snapshot() {
    }

    public Snapshot(Date lastEventDate) {
        this.lastEventDate = lastEventDate;
    }

    public Date getLastEventDate() {
        return lastEventDate;
    }

    public void setLastEventDate(Date lastEventDate) {
        this.lastEventDate = lastEventDate;
    }

    public Map<String, Object> toMap(){
        Map<String, Object> fields = new HashMap<>();
        fields.put("userId", super.getUserId());
        fields.put("totalPoints", super.getTotalPoints());
        fields.put("lastEventDate", this.getLastEventDate());
        return fields;
    }

    public UserModel toUserModel(){
        UserModel userModel = new UserModel();
        userModel.setUserId(super.getUserId());
        userModel.setTotalPoints(super.getTotalPoints());
        return userModel;
    }
}
