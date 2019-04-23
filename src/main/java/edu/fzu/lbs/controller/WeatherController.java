package edu.fzu.lbs.controller;

import edu.fzu.lbs.dao.WeatherInfoDao;
import edu.fzu.lbs.entity.dto.ResultDTO;
import edu.fzu.lbs.entity.po.WeatherInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    private WeatherInfoDao weatherInfoDao;

    @Autowired
    public void setWeatherInfoDao(WeatherInfoDao weatherInfoDao) {
        this.weatherInfoDao = weatherInfoDao;
    }

    @GetMapping
    public ResultDTO<WeatherInfo> get(@RequestParam String city) {
        WeatherInfo weatherInfo = weatherInfoDao.findByCity(city);
        return new ResultDTO<>(weatherInfo);
    }
}
