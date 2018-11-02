package edu.fzu.lbs.service;

import edu.fzu.lbs.config.exception.MyException;
import edu.fzu.lbs.config.exception.ResultEnum;
import edu.fzu.lbs.dao.UserAuthDao;
import edu.fzu.lbs.dao.UserDao;
import edu.fzu.lbs.entity.dto.ResultDTO;
import edu.fzu.lbs.entity.param.PageParam;
import edu.fzu.lbs.entity.param.UserParam;
import edu.fzu.lbs.entity.po.User;
import edu.fzu.lbs.entity.po.UserAuth;
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

@Service
public class UserService {

    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    private UserAuthDao userAuthDao;

    @Autowired
    public void setUserAuthDao(UserAuthDao userAuthDao) {
        this.userAuthDao = userAuthDao;
    }


    public User get(Long id) {
        Optional<User> optional = userDao.findById(id);
        return optional.orElse(null);
    }

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

    public void deleteById(Long id) {
        userDao.deleteById(id);
    }

    public ResultDTO<String> login(UserAuth userAuth) {
        String username = userAuth.getUsername();
        Optional<UserAuth> userOptional = userAuthDao.findByUsername(username);
        if (!userOptional.isPresent()) {
            return ResultDTO.error(ResultEnum.LOGIN_ERROR);
        }

        String password = userOptional.get().getPassword();
        if (password.equals(userAuth.getPassword())) {
            String token = JwtTokenUtil.createToken(username, password);
            return new ResultDTO<>(token);
        } else {
            return ResultDTO.error(ResultEnum.LOGIN_ERROR);
        }
    }

    @Transactional
    public void register(User user, UserAuth userAuth)  {
        String username = user.getUsername();
        Optional<UserAuth> userAuthOptional = userAuthDao.findByUsername(username);
        Optional<User> userOptional = userDao.findByUsername(username);

        //判断用户名是否已经注册
        if (userAuthOptional.isPresent() || userOptional.isPresent()) {
            throw new MyException(ResultEnum.DUPLICATE_ID);
        }

        //TODO:完善注册流程

//        //注册百度鹰眼设备
//        ResultDTO resultDTO = new HttpPostUtil("http://yingyan.baidu.com/api/v3/entity/add")
//                .addParam("ak", APP_KEY_BAIDU)
//                .addParam("service_id", SERVICE_ID_EAGLE_EYE)
//                .addParam("entity_name", username)
//                .post();
//        if (resultDTO.getStatus() != 0) {
//            throw new MyException(ResultEnum.UN_KNOW_ERROR);
//        }

        userDao.saveAndFlush(user);
        userAuthDao.saveAndFlush(userAuth);
    }

    @Transactional
    public void update(User user, UserAuth userAuth)  {
        if (user.getId() == null) {
            register(user, userAuth);
        } else {
            userDao.saveAndFlush(user);
            userAuthDao.saveAndFlush(userAuth);
        }
    }

}
