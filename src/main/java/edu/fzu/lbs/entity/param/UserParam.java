package edu.fzu.lbs.entity.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel
@Data
public class UserParam {

    @ApiModelProperty("用户名")
    String username;

    @ApiModelProperty("姓名")
    String name;
}
