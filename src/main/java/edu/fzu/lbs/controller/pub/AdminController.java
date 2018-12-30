package edu.fzu.lbs.controller.pub;

import edu.fzu.lbs.config.exception.ResultEnum;
import edu.fzu.lbs.dao.AdminDao;
import edu.fzu.lbs.entity.dto.ResultDTO;
import edu.fzu.lbs.entity.po.Admin;
import edu.fzu.lbs.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * 管理员登录接口
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
    private AdminDao adminDao;

    @Autowired
    public AdminController(AdminDao adminDao) {
        this.adminDao = adminDao;
    }

    /**
     * 管理员登录
     *
     * @param username 用户名
     * @param pass     密码
     * @return token
     */
    @PostMapping("/login")
    public ResultDTO login(@RequestParam String username, @RequestParam String pass) {
        Optional<Admin> optional = adminDao.findByUsername(username);
        if (!optional.isPresent()) {
            return ResultDTO.error(ResultEnum.LOGIN_ERROR);
        }

        String password = optional.get().getPassword();
        if (password.equals(pass)) {
            String token = JwtTokenUtil.createToken(username, password);
            return new ResultDTO<>(token);
        } else {
            return ResultDTO.error(ResultEnum.LOGIN_ERROR);
        }
    }
}
