package com.example.demo.dto.event;

import java.io.Serializable;

public class BalanceVariation implements Serializable {
    private static final long serialVersionUID = -1680920060455785106L;

    private Integer points;

    public BalanceVariation(Integer points) {
        this.points = points;
    }

    public BalanceVariation() {
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }
}
