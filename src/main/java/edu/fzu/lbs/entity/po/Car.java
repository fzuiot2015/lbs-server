package edu.fzu.lbs.entity.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@ApiModel(description = "车辆")
@Data
@Entity
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty("用户ID")
    private Long userId;

    @ApiModelProperty(value = "VIN码", required = true)
    private String vin;

    @ApiModelProperty(value = "车牌号", required = true)
    private String plate;

    @ApiModelProperty(value = "车辆类型", required = true)
    private String vehicleType;

    @ApiModelProperty(value = "发动机号", required = true)
    private String engine;

    @ApiModelProperty(value = "车型", required = true)
    private String model;
}
