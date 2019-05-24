package edu.fzu.lbs.entity.param;

import lombok.Data;

import java.util.Date;

/**
 * 保险记录查询参数
 */
@Data
public class InsuranceParam {
    /**
     * 用户id
     */
    private Long userId;

    /**
     * 保险公司
     */
    private String insurer;

    /**
     * 保单号
     */
    private String policyId;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;
}
