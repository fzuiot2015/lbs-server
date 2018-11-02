package edu.fzu.lbs.dao;

import edu.fzu.lbs.entity.po.Accident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface AccidentDao extends JpaRepository<Accident, Long>, JpaSpecificationExecutor<Accident> {
    List<Accident> findByUserId(Long userId);
}
