package edu.fzu.lbs.entity.param;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 违章记录查询参数
 */
@Data
public class ViolationParam {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 时间范围下限
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date minTime;
    /**
     * 时间范围上限
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date maxTime;

}
