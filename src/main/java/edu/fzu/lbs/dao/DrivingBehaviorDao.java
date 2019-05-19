package edu.fzu.lbs.dao;

import edu.fzu.lbs.entity.po.DrivingBehavior;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DrivingBehaviorDao extends JpaRepository<DrivingBehavior, Long> {
    List<DrivingBehavior> findByEntityNameOrderByDateDesc(String entityName, Pageable pageable);
}
