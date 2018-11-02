package edu.fzu.lbs.config.interceptor;

import edu.fzu.lbs.config.exception.MyException;
import edu.fzu.lbs.config.exception.ResultEnum;
import edu.fzu.lbs.dao.AdminDao;
import edu.fzu.lbs.entity.po.Admin;
import edu.fzu.lbs.util.JwtTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Component
public class AdminJwtInterceptor implements HandlerInterceptor {

    private AdminDao adminDao;

    private Logger logger = LoggerFactory.getLogger(AdminJwtInterceptor.class);

    @Autowired
    public AdminJwtInterceptor(AdminDao adminDao) {
        this.adminDao = adminDao;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("login_token");

        if (token == null) {
            throw new MyException(ResultEnum.MISSING_TOKEN);
        }

        String username = JwtTokenUtil.getUsername(token);
        Optional<Admin> optional = adminDao.findByUsername(username);
        if (!optional.isPresent()) {
            throw new MyException(ResultEnum.INCORRECT_TOKEN);
        }
        Admin admin = optional.get();
        String password = admin.getPassword();
        JwtTokenUtil.verifyToken(token, username, password);

        logger.info("admin");
        return true;
    }
}
