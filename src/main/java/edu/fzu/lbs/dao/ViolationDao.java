package edu.fzu.lbs.dao;

import edu.fzu.lbs.entity.po.Accident;
import edu.fzu.lbs.entity.po.Violation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * 事故记录DAO
 */
public interface ViolationDao extends JpaRepository<Violation, Long>, JpaSpecificationExecutor<Violation> {
    /**
     * 根据用户id查询事故记录
     *
     * @param userId 用户id
     * @return 事故记录集合
     */
    List<Violation> findByUserId(Long userId);
}
