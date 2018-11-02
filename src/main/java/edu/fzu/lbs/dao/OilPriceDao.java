package edu.fzu.lbs.dao;

import edu.fzu.lbs.entity.po.OilPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OilPriceDao extends JpaRepository<OilPrice, Long> {
    OilPrice findOilPriceByProvince(String province);

    @Query("SELECT province FROM OilPrice")
    List<String> findProvinces();
}
