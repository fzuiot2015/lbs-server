package edu.fzu.lbs.dao;

import edu.fzu.lbs.entity.po.YearlyInspection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface YearlyInspectionDao extends JpaRepository<YearlyInspection, Long>, JpaSpecificationExecutor<YearlyInspection> {
    List<YearlyInspection> findByUserIdOrderByNextDateDesc(Long userId);
}
