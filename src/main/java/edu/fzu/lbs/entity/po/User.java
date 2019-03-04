package edu.fzu.lbs.entity.po;

import lombok.Data;

import javax.persistence.*;

/**
 * 用户信息
 */
@Data
@Entity
@Table(name = "user")
public class User {
    /**
     * 用户id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 账号
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 姓名
     */
    private String name;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 驾驶证号
     */
    private String driverLicense;

}
