package edu.fzu.lbs.config.exception;

/**
 * 自定义异常类
 */
public class MyException extends RuntimeException {
    private ResultEnum resultEnum;

    public MyException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.resultEnum = resultEnum;
    }

    public ResultEnum getResultEnum() {
        return resultEnum;
    }

}
