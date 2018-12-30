package edu.fzu.lbs.dao;

import edu.fzu.lbs.entity.po.InsuranceCompany;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 保险公司DAO
 */
public interface InsuranceCompanyDao  extends JpaRepository<InsuranceCompany, Long> {
}
