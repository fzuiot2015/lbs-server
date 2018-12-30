package edu.fzu.lbs.dao;

import edu.fzu.lbs.entity.po.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * 管理员DAO
 */
public interface AdminDao extends JpaRepository<Admin, Long> {
    /**
     * 根据用户名查询管理员
     *
     * @param username 管理员用户名
     * @return 管理员对象
     */
    Optional<Admin> findByUsername(String username);
}
