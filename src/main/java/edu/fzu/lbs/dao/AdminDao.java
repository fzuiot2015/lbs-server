package edu.fzu.lbs.dao;

import edu.fzu.lbs.entity.po.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminDao extends JpaRepository<Admin, Long> {
    Optional<Admin> findByUsername(String username);
}
