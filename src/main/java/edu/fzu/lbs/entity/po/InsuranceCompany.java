package edu.fzu.lbs.entity.po;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "insurance_company")
public class InsuranceCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Float loss;         //车辆损失险  	现款购车价格×1.2%
    private Float burglary;     //全车盗抢险     新车购置价×1.0%
    private Float glass;        //玻璃单独破碎险  进口新车购置价×0.25%，国产新车购置价×0.15%
    private Float fire;         //自燃损失险     新车购置价×0.15%
    private Float person;       //车上人员责任险  每人保费50元，可根据车辆的实际座位数填写
}
