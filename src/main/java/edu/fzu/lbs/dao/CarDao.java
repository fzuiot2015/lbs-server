package edu.fzu.lbs.dao;

import edu.fzu.lbs.entity.po.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 车辆信息DAO
 */
public interface CarDao extends JpaRepository<Car, Long> {
    /**
     * 根据车主用户id查询车辆信息
     *
     * @param userId 用户id
     * @return 车辆信息集合
     */
    List<Car> findByUserId(Long userId);
}
