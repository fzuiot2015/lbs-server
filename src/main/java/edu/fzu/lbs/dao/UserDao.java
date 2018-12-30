package edu.fzu.lbs.dao;

import edu.fzu.lbs.entity.po.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

/**
 * 用户信息DAO
 */
public interface UserDao extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    /**
     * 根据用户名查询用户信息
     *
     * @param username 　用户名
     * @return 用户信息
     */
    Optional<User> findByUsername(String username);
}
