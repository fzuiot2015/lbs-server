package edu.fzu.lbs.dao;

import edu.fzu.lbs.entity.po.Tips;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipsDao extends JpaRepository<Tips,Long> {
}
