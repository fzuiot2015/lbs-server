package edu.fzu.lbs.entity.dto;

import lombok.Data;

@Data
public class LimitMsg {
    private boolean Limited;
    private String limitSuffix;

    public LimitMsg(boolean Limited, String limitSuffix) {
        this.Limited = Limited;
        this.limitSuffix = limitSuffix;
    }
}
