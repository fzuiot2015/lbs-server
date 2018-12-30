package edu.fzu.lbs.entity.po;

import lombok.Data;

import javax.persistence.*;

/**
 * 油价信息
 */
@Data
@Entity
@Table(name = "oil_price")
public class OilPrice {
    /**
     * 油价信息id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 省份
     */
    private String province;

    /**
     * 0号柴油价格
     */
    private Float dieselOil0;

    /**
     * 90号汽油价格
     */
    private Float gasoline90;

    /**
     * 93号汽油价格
     */
    private Float gasoline93;

    /**
     * 97号汽油价格
     */
    private Float gasoline97;
}
