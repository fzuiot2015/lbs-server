package edu.fzu.lbs.entity.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.domain.PageRequest;

@ApiModel(description = "分页参数")
@Data
public class PageParam {

    @ApiModelProperty("分页页码,默认为0")
    private Integer pageNum = 0;

    @ApiModelProperty("分页容量,默认为5")
    private Integer pageSize = 5;

    public PageRequest toPageRequest() {
        return PageRequest.of(pageNum, pageSize);
    }

}
