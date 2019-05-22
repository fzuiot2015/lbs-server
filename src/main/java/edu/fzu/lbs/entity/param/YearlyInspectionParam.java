package edu.fzu.lbs.entity.param;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class YearlyInspectionParam {
    /**
     * 用户id
     */
    private Long userId;

    /**
     * 时间范围下限
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date minDate;
    /**
     * 时间范围上限
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date maxDate;
}
