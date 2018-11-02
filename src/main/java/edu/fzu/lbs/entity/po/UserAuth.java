package edu.fzu.lbs.entity.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

@ApiModel(description = "用户认证")
@Data
@Entity
@Table(name = "user_auth")
public class UserAuth {

    @ApiModelProperty(value = "用户ID", hidden = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty(value = "账号", required = true)
    private String username;

    @ApiModelProperty(value = "密码", required = true)
    private String password;
}
