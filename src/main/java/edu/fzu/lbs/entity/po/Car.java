package edu.fzu.lbs.entity.po;

import lombok.Data;

import javax.persistence.*;

/**
 * 车辆信息
 */
@Data
@Entity
@Table(name = "car")
public class Car {
    /**
     * 车辆id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 车主用户ID
     */
    private Long userId;

    /**
     * VIN码
     */
    private String vin;

    /**
     * 车牌号
     */
    private String plate;

    /**
     * 车辆类型
     */
    private String vehicleType;

    /**
     * 发动机号
     */
    private String engine;

    /**
     * 车型
     */
    private String model;
}
