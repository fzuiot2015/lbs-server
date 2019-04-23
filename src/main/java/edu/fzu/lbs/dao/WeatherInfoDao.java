package edu.fzu.lbs.dao;

import edu.fzu.lbs.entity.po.WeatherInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherInfoDao extends JpaRepository<WeatherInfo, Long> {
    WeatherInfo findByCity(String city);
}
