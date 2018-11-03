package edu.fzu.lbs.config.interceptor;


import edu.fzu.lbs.config.exception.MyException;
import edu.fzu.lbs.config.exception.ResultEnum;
import edu.fzu.lbs.dao.UserDao;
import edu.fzu.lbs.entity.po.User;
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
public class UserJwtInterceptor implements HandlerInterceptor {

    private Logger logger = LoggerFactory.getLogger(UserJwtInterceptor.class);

    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("login_token");

        if (token == null) {
            throw new MyException(ResultEnum.MISSING_TOKEN);
        }

        String username = JwtTokenUtil.getUsername(token);
        Optional<User> userOptional = userDao.findByUsername(username);
        if (!userOptional.isPresent()) {
            throw new MyException(ResultEnum.INCORRECT_TOKEN);
        }
        User user = userOptional.get();
        String password = user.getPassword();
        JwtTokenUtil.verifyToken(token, username, password);

        Long userId = user.getId();
        logger.info("userId:" + userId.toString());
        request.setAttribute("userId", userId);
        return true;
    }
}
