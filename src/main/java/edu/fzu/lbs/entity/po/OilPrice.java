package edu.fzu.lbs.entity.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 油价数据POJO
 */
@ApiModel(description = "油价")
@Data
@Entity
@Table(name = "oil_price")
public class OilPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty("省份")
    private String province;    //省份

    @ApiModelProperty("0号柴油价格")
    private Float dieselOil0;        //0号柴油价格

    @ApiModelProperty("90号汽油价格")
    private Float gasoline90;       //90号汽油价格

    @ApiModelProperty("93号汽油价格")
    private Float gasoline93;       //93号汽油价格

    @ApiModelProperty("97号汽油价格")
    private Float gasoline97;       //97号汽油价格
}
