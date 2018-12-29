package edu.fzu.lbs.entity.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@ApiModel(description = "事故记录")
@Data
@Entity
@Table(name = "accident")
public class Accident {

    @ApiModelProperty("事故ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty(value = "用户ID", required = true)
    private Long userId;

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    @ApiModelProperty(value = "时间", required = true)
    private Date time;

    @ApiModelProperty(value = "纬度值", required = true)
    private Float lat;

    @ApiModelProperty(value = "经度值", required = true)
    private Float lng;

    @ApiModelProperty(value = "地点", required = true)
    private String address;

    @ApiModelProperty(value = "详情描述", required = true)
    private String description;
}
