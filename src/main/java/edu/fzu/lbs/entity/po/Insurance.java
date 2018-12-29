package edu.fzu.lbs.entity.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@ApiModel(description = "保险信息")
@Data
@Entity
@Table(name = "insurance")
public class Insurance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty("用户ID")
    private Long userId;

    @ApiModelProperty(value = "保险公司", required = true)
    private String insurer;

    @ApiModelProperty(value = "保单号", required = true)
    private String policyId;

    @ApiModelProperty(value = "保险电话", required = true)
    private String insurancePhone;

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "开始时间", required = true)
    @Temporal(TemporalType.DATE)
    private Date startTime;

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "结束时间", required = true)
    @Temporal(TemporalType.DATE)
    private Date endTime;
}
