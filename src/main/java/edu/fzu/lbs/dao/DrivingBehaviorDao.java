package edu.fzu.lbs.dao;

import edu.fzu.lbs.entity.po.DrivingBehavior;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrivingBehaviorDao extends JpaRepository<DrivingBehavior, Long> {
}
