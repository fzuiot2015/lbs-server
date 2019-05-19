package edu.fzu.lbs.entity.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 驾驶行为
 */
@Data
@Entity
@Table(name = "driving_behavior")
public class DrivingBehavior {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 日期
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date date;

    private String entityName;

    /**
     * 行程里程,单位米
     */
    private double distance;
    /**
     * 行程耗时,单位秒
     */
    private int duration;

    /**
     * 平均时速，单位 km/h
     */
    @SerializedName("average_speed")
    private double averageSpeed;

    /**
     * 最高时速,单位 km/h
     */
    @SerializedName("max_speed")
    private double maxSpeed;

    /**
     * 超速次数
     */
    @SerializedName("speeding_num")
    private int speedingNum;

    /**
     * 急加速次数
     */
    @SerializedName("harsh_acceleration_num")
    private int harshAccelerationNum;

    /**
     * 急刹车次数
     */
    @SerializedName("harsh_breaking_num")
    private int harshBreakingNum;

    /**
     * 急转弯次数
     */
    @SerializedName("harsh_steering_num")
    private int harshSteeringNum;

    public DrivingBehavior() {
    }

    public DrivingBehavior(double distance, int duration, double averageSpeed, double maxSpeed, int speedingNum, int harshAccelerationNum, int harshBreakingNum, int harshSteeringNum) {
        this.distance = distance;
        this.duration = duration;
        this.averageSpeed = averageSpeed;
        this.maxSpeed = maxSpeed;
        this.speedingNum = speedingNum;
        this.harshAccelerationNum = harshAccelerationNum;
        this.harshBreakingNum = harshBreakingNum;
        this.harshSteeringNum = harshSteeringNum;
    }
}
