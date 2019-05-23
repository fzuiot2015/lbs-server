package edu.fzu.lbs.entity.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 事故记录
 */
@Data
@Entity
@Table(name = "accident")
public class Accident {

    /**
     * 事故ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;

    /**
     * 纬度值
     */
    private Float lat;

    /**
     * 经度值
     */
    private Float lng;

    /**
     * 地点
     */
    private String address;

    /**
     * 详情描述
     */
    private String description;

    /**
     * 图片地址
     */
    private String photoUrl;
}
