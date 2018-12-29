package edu.fzu.lbs.dao;

import edu.fzu.lbs.entity.po.InsuranceCompany;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsuranceCompanyDao  extends JpaRepository<InsuranceCompany, Long> {
}
