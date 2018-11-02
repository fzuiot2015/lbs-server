package edu.fzu.lbs.dao;

import edu.fzu.lbs.entity.po.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAuthDao extends JpaRepository<UserAuth, Long> {
    Optional<UserAuth> findByUsername(String username);
}
