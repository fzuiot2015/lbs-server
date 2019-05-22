package edu.fzu.lbs.controller;


import edu.fzu.lbs.entity.dto.ResultDTO;
import edu.fzu.lbs.entity.param.PageParam;
import edu.fzu.lbs.entity.param.UserParam;
import edu.fzu.lbs.entity.po.User;
import edu.fzu.lbs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 * 用户接口
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * 用户登录
     *
     * @param username 用户名
     * @param pass     密码
     * @return 若登录成功，结果包含token
     */
    @PostMapping("/login")
    public ResultDTO login(@RequestParam String username, @RequestParam String pass) {
        return userService.login(username, pass);
    }

    /**
     * 用户注册
     *
     * @param username      用户名
     * @param password      密码
     * @param name          姓名
     * @param phone         手机号
     * @param driverLicense 驾驶证号
     * @return 若登录成功，结果包含token
     */
    @PostMapping("/register")
    public ResultDTO register(@RequestParam String username,
                              @RequestParam String password,
                              @RequestParam String name,
                              @RequestParam String phone,
                              @RequestParam String driverLicense) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setName(name);
        user.setPhone(phone);
        user.setDriverLicense(driverLicense);
        return userService.register(user);
    }

    /**
     * 保存或更新一条用户信息
     *
     * @param user 用户对象
     * @return
     */
    @PostMapping
    public ResultDTO put(@RequestBody @Valid User user) {
        userService.update(user);
        return new ResultDTO();
    }

    /**
     * 删除一条用户信息
     *
     * @param id 用户id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResultDTO delete(@PathVariable Long id) {
        userService.deleteById(id);
        return new ResultDTO();
    }


    /**
     * 根据查询条件及分页参数获取用户信息列表
     *
     * @param userParam 用户查询参数
     * @param pageParam 分页参数
     * @return 用户信息集合
     */
    @GetMapping("/list")
    public ResultDTO<List<User>> list(UserParam userParam, PageParam pageParam) {
        Page<User> page = userService.getList(userParam, pageParam);
        return new ResultDTO<>(page.getContent(), page.getNumber(), page.getTotalPages(), page.getTotalElements());
    }

    /**
     * 通过指定ID获取用户信息
     *
     * @param id 用户ID
     * @return 用户信息
     */
    @GetMapping("/{id}")
    public ResultDTO<User> getById(@PathVariable("id") Long id) {
        User user = userService.getById(id);
        return new ResultDTO<>(user);
    }

    /**
     * 通过用户名获取用户信息
     *
     * @param username 用户名
     * @return 用户信息
     */
    @GetMapping
    public ResultDTO<User> getByUsername(String username) {
        User user = userService.getByUsername(username);
        return new ResultDTO<>(user);
    }
}
