package edu.fzu.lbs.controller;


import edu.fzu.lbs.entity.dto.ResultDTO;
import edu.fzu.lbs.entity.param.PageParam;
import edu.fzu.lbs.entity.param.UserParam;
import edu.fzu.lbs.entity.po.User;
import edu.fzu.lbs.entity.po.UserAuth;
import edu.fzu.lbs.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Api(tags = "用户")
@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "登录", notes = "通过账号密码登录，登录成功则返回对应的Token")
    @PostMapping("/login")
    public ResultDTO login(@Valid UserAuth userAuth) {
        return userService.login(userAuth);
    }

    @ApiOperation(value = "注册", notes = "注册新用户")
    @PostMapping("/register")
    public ResultDTO register(@Valid User user, @Valid UserAuth userAuth) throws IOException {
        userService.register(user, userAuth);
        return new ResultDTO();
    }

    @PutMapping
    public ResultDTO put(@RequestBody @Valid User user, @RequestBody @Valid UserAuth userAuth) {
        userService.update(user, userAuth);
        return new ResultDTO();
    }

    @DeleteMapping("/{id}")
    public ResultDTO delete(@PathVariable Long id) {
        userService.deleteById(id);
        return new ResultDTO();
    }

    @ApiOperation(value = "获取列表", notes = "可根据查询条件及分页参数获取事故记录列表")
    @GetMapping
    public ResultDTO<List<User>> query(UserParam userParam, PageParam pageParam) {
        Page<User> page = userService.getList(userParam, pageParam);
        return new ResultDTO<>(page.getContent(), page.getNumber(), page.getTotalPages(), page.getTotalElements());
    }

    /**
     * 通过指定ID获取实体信息
     *
     * @param id 实体ID
     */
    @ApiOperation(value = "根据ID获取实体", notes = "通过指定ID获取实体信息")
    @ApiImplicitParam(name = "id", value = "ID", required = true)
    @GetMapping("/{id}")
    public ResultDTO<User> get(@PathVariable("id") Long id) {
        User user = userService.get(id);
        return new ResultDTO<>(user);
    }

    // TODO: 2018/11/1 PUT方法
}
