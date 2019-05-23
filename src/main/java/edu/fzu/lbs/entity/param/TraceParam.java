package edu.fzu.lbs.entity.param;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 驾驶行为查询参数
 */
@Data
public class TraceParam {


    private String entityName;

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
