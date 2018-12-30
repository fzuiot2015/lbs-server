package edu.fzu.lbs.dao;

import edu.fzu.lbs.entity.po.OilPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 油价信息DAO
 */
public interface OilPriceDao extends JpaRepository<OilPrice, Long> {
    /**
     * 根据省份查询油价信息
     *
     * @param province 　省份
     * @return 油价信息
     */
    OilPrice findOilPriceByProvince(String province);

    /**
     * 获取支持的所有省份
     *
     * @return 省份名称集合
     */
    @Query("SELECT province FROM OilPrice")
    List<String> findProvinces();
}
