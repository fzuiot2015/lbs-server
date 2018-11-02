package edu.fzu.lbs.entity.dto;


import com.google.gson.annotations.SerializedName;
import edu.fzu.lbs.config.exception.ResultEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 反馈结果类，用于服务器端与安卓端的消息通信
 */
@ApiModel(description = "用于封装请求的结果信息")
@Data
public class ResultDTO<T> {

    @ApiModelProperty("状态码")
    @SerializedName(value = "status", alternate = "error_code")
    private int status;

    @ApiModelProperty("状态信息")
    @SerializedName(value = "message", alternate = "reason")
    private String message;

    @ApiModelProperty("返回内容")
    @SerializedName(value = "results", alternate = "result")
    private T result;

    @ApiModelProperty("当前分页页码(首页页码为0)")
    private Integer pageNum;

    @ApiModelProperty("分页总页数")
    private Integer totalPages;

    @ApiModelProperty("项目总数")
    private Long total;

    public ResultDTO() {
        setResultEnum(ResultEnum.SUCCESS);
    }

    public ResultDTO(T result) {
        setResultEnum(ResultEnum.SUCCESS);
        setResult(result);
    }

    public ResultDTO(T result, int pageNum, int totalPages, long total) {
        setResultEnum(ResultEnum.SUCCESS);
        setResult(result);
        setPageNum(pageNum);
        setTotalPages(totalPages);
        setTotal(total);
    }

    public static ResultDTO error(ResultEnum resultEnum) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setResultEnum(resultEnum);
        return resultDTO;

    }

    private void setResultEnum(ResultEnum resultEnum) {
        this.status = resultEnum.getStatus();
        this.message = resultEnum.getMessage();
    }
}
