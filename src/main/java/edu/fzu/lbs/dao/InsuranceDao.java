package edu.fzu.lbs.dao;

import edu.fzu.lbs.entity.po.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface InsuranceDao extends JpaRepository<Insurance, Long>, JpaSpecificationExecutor<Insurance> {
    List<Insurance> findByUserId(Long userId);
}
