package edu.fzu.lbs.config.exception;

/**
 * 反馈结果枚举，统一管理反馈结果的状态码
 */
public enum ResultEnum {
    SUCCESS(0, "成功"),
    UN_KNOW_ERROR(-1, "未知错误"),
    FORMAT_ERROR(100, "格式不符合要求"),
    DUPLICATE_ID(101, "该账号已被注册"),
    LOGIN_ERROR(102, "账号或密码错误"),
    TIMEOUT(103, "请求超时"),
    MISSING_PARAM(104, "缺少请求参数"),
    MISSING_TOKEN(105, "缺少Token"),
    INCORRECT_TOKEN(106, "Token不正确"),
    TOKEN_EXPIRED(107, "Token过期"),
    ID_ERROR(108, "ID错误"),
    ;

    private int status;
    private String message;

    ResultEnum(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
