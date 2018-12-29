package edu.fzu.lbs.entity.dto;

import lombok.Data;

@Data
public class LoginResult {
    Long userId;
    String token;

    public LoginResult(Long userId, String token) {
        this.userId = userId;
        this.token = token;
    }
}
