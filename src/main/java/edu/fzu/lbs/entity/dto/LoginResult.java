package edu.fzu.lbs.entity.dto;

import lombok.Data;

/**
 * 用户登录反馈
 */
@Data
public class LoginResult {
    Long userId;    //用户id
    String token;   //token

    public LoginResult(Long userId, String token) {
        this.userId = userId;
        this.token = token;
    }
}
