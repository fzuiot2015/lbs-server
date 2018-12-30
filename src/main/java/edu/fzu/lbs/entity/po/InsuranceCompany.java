package edu.fzu.lbs.entity.po;

import lombok.Data;

import javax.persistence.*;

/**
 * 保险公司保险规则
 */
@Data
@Entity
@Table(name = "insurance_company")
public class InsuranceCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    /**
     * 车辆损失险系数
     * 通常为现款购车价格×1.2%
     */
    private Float loss;
    /**
     * 全车盗抢险系数
     * 通常为新车购置价×1.0%
     */
    private Float burglary;

    /**
     * 玻璃单独破碎险系数
     * 通常进口新车购置价×0.25%，国产新车购置价×0.15%
     */
    private Float glass;

    /**
     * 自燃损失险系数
     * 通常为新车购置价×0.15%
     */
    private Float fire;

    /**
     * 车上人员责任险系数
     * 通常每人保费50元
     */
    private Float person;
}
