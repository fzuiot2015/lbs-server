package edu.fzu.lbs.service;

import edu.fzu.lbs.dao.InsuranceDao;
import edu.fzu.lbs.entity.param.InsuranceParam;
import edu.fzu.lbs.entity.param.PageParam;
import edu.fzu.lbs.entity.po.Insurance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 保险记录Service
 */
@Service
public class InsuranceService {
    private InsuranceDao insuranceDao;

    @Autowired
    public void setInsuranceDao(InsuranceDao insuranceDao) {
        this.insuranceDao = insuranceDao;
    }

    /**
     * 根据条件查询保险记录
     *
     * @param insuranceParam 保险记录查询参数
     * @param pageParam      分页参数
     * @return 保险记录集合
     */
    public Page<Insurance> getList(InsuranceParam insuranceParam, PageParam pageParam) {
        Pageable pageable = pageParam.toPageRequest();

        if (insuranceParam == null) {
            return insuranceDao.findAll(pageable);
        }

        //动态添加查询参数
        Specification<Insurance> specification = (Specification<Insurance>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();

            Path<Long> userIdPath = root.get("userId");
            Long userId = insuranceParam.getUserId();
            if (userId != null) {
                predicateList.add(criteriaBuilder.equal(userIdPath, userId));
            }

            Path<String> insurerPath = root.get("insurer");
            String insurer = insuranceParam.getInsurer();
            if (insurer != null) {
                predicateList.add(criteriaBuilder.equal(insurerPath, insurer));
            }

            Path<String> policyIdPath = root.get("policyId");
            String policyId = insuranceParam.getPolicyId();
            if (policyId != null) {
                predicateList.add(criteriaBuilder.equal(policyIdPath, policyId));
            }

            Path<Date> timePath = root.get("startTime");
            Date startTime = insuranceParam.getStartTime();
            if (startTime != null) {
                predicateList.add(criteriaBuilder.greaterThanOrEqualTo(timePath, startTime));
            }
            Date endTime = insuranceParam.getEndTime();
            if (endTime != null) {
                predicateList.add(criteriaBuilder.lessThanOrEqualTo(timePath, endTime));
            }

            int predicateSize = predicateList.size();
            Predicate[] predicateArray = predicateList.toArray(new Predicate[predicateSize]);
            return criteriaBuilder.and(predicateArray);
        };
        return insuranceDao.findAll(specification, pageable);
    }

    /**
     * 保存或更新一条保险记录
     *
     * @param insurance 保险记录
     */
    public void update(Insurance insurance) {
        insuranceDao.saveAndFlush(insurance);
    }

    /**
     * 根据id删除一条保险记录
     *
     * @param id 保险记录id
     */
    public void deleteById(Long id) {
        insuranceDao.deleteById(id);
    }

    /**
     * 根据用户id查询保险记录
     *
     * @param userId 用户id
     * @return 保险记录
     */
    public List<Insurance> findByUserId(Long userId) {
        return insuranceDao.findByUserIdOrderByEndTimeDesc(userId);
    }

    /**
     * 根据用户id判断保险是否到期或即将到期（一个月内到期）
     *
     * @param userId 用户id
     * @return 到期或即将到期的保险记录
     */
    public Insurance isExpire(Long userId) {
        List<Insurance> insuranceList = insuranceDao.findByUserIdOrderByEndTimeDesc(userId);
        if (insuranceList.isEmpty()) {
            return null;
        }
        Insurance insurance = insuranceList.get(0);
        Date endTime = insurance.getEndTime();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(endTime);
        Instant instant = calendar.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDate endLocalDate = instant.atZone(zoneId).toLocalDate();
        LocalDate nowLocalDate = LocalDate.now();

        Period period = Period.between(nowLocalDate, endLocalDate);
        int months = period.getMonths();
        if (months < 1) {
            return insurance;
        } else {
            return null;
        }
    }
}
