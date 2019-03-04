package edu.fzu.lbs.dao;

import edu.fzu.lbs.entity.po.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * 保险记录DAO
 */
public interface InsuranceDao extends JpaRepository<Insurance, Long>, JpaSpecificationExecutor<Insurance> {
    /**
     * 根据用户id查询保险记录
     *
     * @param userId 用户id
     * @return 保险记录集合
     */
    List<Insurance> findByUserIdOrderByEndTimeDesc(Long userId);
}
