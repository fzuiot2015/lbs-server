package edu.fzu.lbs.entity.param;

import lombok.Data;

/**
 * 用户信息查询参数
 */
@Data
public class UserParam {
    /**
     * 用户名
     */
    String username;

    /**
     * 姓名
     */
    String name;
}
