package edu.fzu.lbs.entity.po;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "limit_rule")
public class LimitRule {
    /**
     * 限行规则id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 城市名称
     */
    private String city;

    /**
     * 允许通行的车牌前缀
     */
    private String platePrefix;

    /**
     * 限行尾号
     */
    private String limitSuffix;
}
