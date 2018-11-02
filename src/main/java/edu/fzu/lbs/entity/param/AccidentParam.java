package edu.fzu.lbs.entity.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@ApiModel
@Data
public class AccidentParam {

    @ApiModelProperty("用户ID")
    Long userId;

    @ApiModelProperty("时间范围下限")
    Date minTime;

    @ApiModelProperty("时间范围上限")
    Date maxTime;

}
