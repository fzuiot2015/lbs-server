package edu.fzu.lbs.entity.dto;

import com.google.gson.annotations.SerializedName;
import edu.fzu.lbs.config.exception.ResultEnum;
import lombok.Data;

/**
 * 反馈结果类，用于用于封装HTTP请求的反馈结果信息
 */
@Data
public class ResultDTO<T> {
    /**
     * 状态码
     */
    @SerializedName(value = "status", alternate = "error_code")
    private int status;

    /**
     * 状态信息
     */
    @SerializedName(value = "message", alternate = {"reason", "msg"})
    private String message;

    /**
     * 返回内容
     */
    @SerializedName(value = "results", alternate = "result")
    private T result;

    /**
     * 当前分页页码(首页页码为0)
     */
    private Integer pageNum;

    /**
     * 分页总页数
     */
    private Integer totalPages;

    /**
     * 项目总数
     */
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

    /**
     * 创建请求失败的反馈结果
     *
     * @param resultEnum 反馈结果枚举
     * @return 反馈结果
     */
    public static ResultDTO error(ResultEnum resultEnum) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setResultEnum(resultEnum);
        return resultDTO;

    }

    /**
     * 根据反馈结果枚举设置状态码和状态消息
     *
     * @param resultEnum 反馈结果枚举
     */
    private void setResultEnum(ResultEnum resultEnum) {
        this.status = resultEnum.getStatus();
        this.message = resultEnum.getMessage();
    }
}
