package com.example.demo.dto.event;

import java.io.Serializable;

public class BalanceVariationEvent implements Serializable {
    private static final long serialVersionUID = -1680920060455785106L;

    private Integer points;

    public BalanceVariationEvent(Integer points) {
        this.points = points;
    }

    public BalanceVariationEvent() {
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }
}
