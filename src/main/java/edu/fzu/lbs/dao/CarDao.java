package edu.fzu.lbs.dao;

import edu.fzu.lbs.entity.po.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarDao extends JpaRepository<Car, Long> {
    List<Car> findByUserId(Long userId);
}
