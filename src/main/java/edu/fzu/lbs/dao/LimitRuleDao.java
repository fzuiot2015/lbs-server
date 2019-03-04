package edu.fzu.lbs.dao;

import edu.fzu.lbs.entity.po.LimitRule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LimitRuleDao extends JpaRepository<LimitRule, Long> {
    LimitRule findLimitRuleByCity(String city);
}
