package edu.fzu.lbs.service;

import edu.fzu.lbs.config.exception.MyException;
import edu.fzu.lbs.config.exception.ResultEnum;
import edu.fzu.lbs.dao.UserDao;
import edu.fzu.lbs.entity.dto.LoginResult;
import edu.fzu.lbs.entity.dto.ResultDTO;
import edu.fzu.lbs.entity.param.PageParam;
import edu.fzu.lbs.entity.param.UserParam;
import edu.fzu.lbs.entity.po.User;
import edu.fzu.lbs.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 用户信息Service
 */
@Service
public class UserService {

    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * 根据用户名查询用户信息
     *
     * @param username 用户名
     * @return 用户信息
     */
    public User getByUsername(String username) {
        Optional<User> optional = userDao.findByUsername(username);
        return optional.orElse(null);
    }

    /**
     * 根据id查询用户信息
     *
     * @param id 用户id
     * @return 用户信息
     */
    public User getById(Long id) {
        Optional<User> optional = userDao.findById(id);
        return optional.orElse(null);
    }

    /**
     * 根据条件查询用户信息
     *
     * @param userParam 用户查询参数
     * @param pageParam 分页参数
     * @return 用户信息分页对象
     */
    public Page<User> getList(UserParam userParam, PageParam pageParam) {
        Pageable pageable = pageParam.toPageRequest();
        if (userParam == null) {
            return userDao.findAll(pageable);
        }

        Specification<User> specification = (Specification<User>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();

            Path<String> usernamePath = root.get("username");
            String username = userParam.getUsername();
            if (username != null) {
                predicateList.add(criteriaBuilder.equal(usernamePath, username));
            }

            Path<String> namePath = root.get("name");
            String name = userParam.getName();
            if (name != null) {
                predicateList.add(criteriaBuilder.equal(namePath, name));
            }

            int predicateSize = predicateList.size();
            Predicate[] predicateArray = predicateList.toArray(new Predicate[predicateSize]);
            return criteriaBuilder.and(predicateArray);
        };

        return userDao.findAll(specification, pageable);
    }

    /**
     * 根据id删除一条用户信息
     *
     * @param id 用户id
     */
    public void deleteById(Long id) {
        userDao.deleteById(id);
    }

    /**
     * 用户登录
     *
     * @param username 用户名
     * @param pass     密码
     * @return 反馈结果
     */
    public ResultDTO login(String username, String pass) {
        Optional<User> userOptional = userDao.findByUsername(username);
        if (!userOptional.isPresent()) {
            return ResultDTO.error(ResultEnum.LOGIN_ERROR);
        }

        User user = userOptional.get();
        String password = user.getPassword();
        if (password.equals(pass)) {
            String token = JwtTokenUtil.createToken(username, password);
            LoginResult result = new LoginResult(user.getId(), token);
            return new ResultDTO<>(result);
        } else {
            return ResultDTO.error(ResultEnum.LOGIN_ERROR);
        }
    }

    /**
     * 用户注册
     *
     * @param user 用户信息
     * @return 反馈结果
     */
    @Transactional
    public ResultDTO<LoginResult> register(User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        Optional<User> userOptional = userDao.findByUsername(username);

        //判断用户名是否已经注册
        if (userOptional.isPresent()) {
            throw new MyException(ResultEnum.DUPLICATE_ID);
        }
        userDao.saveAndFlush(user);
        String token = JwtTokenUtil.createToken(username, password);
        LoginResult loginResult = new LoginResult(user.getId(), token);
        return new ResultDTO<>(loginResult);
    }

    /**
     * 保存或更新一条用户信息
     *
     * @param user 用户信息
     */
    @Transactional
    public void update(User user) {
        if (user.getId() == null) {
            register(user);
        } else {
            userDao.saveAndFlush(user);
        }
    }

}
