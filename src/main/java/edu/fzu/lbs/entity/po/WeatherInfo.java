package edu.fzu.lbs.entity.po;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "weather_info")
public class WeatherInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String city;
    private String weather;
    private String temp;
}
