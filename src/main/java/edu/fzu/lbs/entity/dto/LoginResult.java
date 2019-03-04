package edu.fzu.lbs.entity.dto;

import lombok.Data;

/**
 * 用户登录反馈
 */
@Data
public class LoginResult {
    /**
     * 用户id
     */
    private Long userId;

    /**
     * token令牌
     */
    private String token;

    public LoginResult(Long userId, String token) {
        this.userId = userId;
        this.token = token;
    }
}
