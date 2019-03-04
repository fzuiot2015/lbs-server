package edu.fzu.lbs.entity.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 保险记录
 */
@Data
@Entity
@Table(name = "insurance")
public class Insurance {
    /**
     * 保险记录id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户ID
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
     * 保险电话
     */
    private String insurancePhone;

    /**
     * 开始时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date startTime;

        /**
         * 结束时间
         */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date endTime;
}
