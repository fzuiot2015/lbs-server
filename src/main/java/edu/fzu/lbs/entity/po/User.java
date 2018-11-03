package edu.fzu.lbs.entity.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@ApiModel(description = "用户")
@Data
@Entity
@Table(name = "user")
public class User {

    @ApiModelProperty("用户ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty(value = "账号", required = true)
    private String username;

    private String password;

    @ApiModelProperty(value = "姓名", required = true)
    private String name;

    @ApiModelProperty(value = "电话", required = true)
    private String phone;

    @ApiModelProperty(value = "住址", required = true)
    private String address;

    @ApiModelProperty(value = "驾驶证号", required = true)
    private String driverLicense;

}
