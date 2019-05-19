package edu.fzu.lbs.dao;

import edu.fzu.lbs.entity.po.CheckStep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CheckStepDao extends JpaRepository<CheckStep, Long> {

    @Query(value = "SELECT content FROM check_step WHERE type>=:type ORDER BY step ASC"
            , nativeQuery = true)
    List<String> findContent(@Param("type") int type);
}
