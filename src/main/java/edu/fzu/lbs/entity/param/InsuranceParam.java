package edu.fzu.lbs.entity.param;

import lombok.Data;

import java.util.Date;

@Data
public class InsuranceParam {
    private Long userId;
    private Date startTime;
    private Date endTime;
}
