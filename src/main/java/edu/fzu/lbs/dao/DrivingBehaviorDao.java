package edu.fzu.lbs.dao;

import edu.fzu.lbs.entity.po.DrivingBehavior;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface DrivingBehaviorDao extends JpaRepository<DrivingBehavior, Long> , JpaSpecificationExecutor<DrivingBehavior> {
    List<DrivingBehavior> findByEntityNameOrderByDateDesc(String entityName, Pageable pageable);
}
