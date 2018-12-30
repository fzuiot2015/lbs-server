package edu.fzu.lbs.entity.param;

import lombok.Data;

import java.util.Date;

/**
 * 事故记录查询参数
 */
@Data
public class AccidentParam {

    /**
     * 用户id
     */
    Long userId;

    /**
     * 时间范围下限
     */
    Date minTime;

    /**
     * 时间范围上限
     */
    Date maxTime;

}
