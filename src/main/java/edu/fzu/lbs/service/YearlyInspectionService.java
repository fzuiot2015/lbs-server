package edu.fzu.lbs.service;

import edu.fzu.lbs.dao.YearlyInspectionDao;
import edu.fzu.lbs.entity.param.PageParam;
import edu.fzu.lbs.entity.param.YearlyInspectionParam;
import edu.fzu.lbs.entity.po.YearlyInspection;
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

@Service
public class YearlyInspectionService {
    private YearlyInspectionDao yearlyInspectionDao;

    @Autowired
    public void setYearlyInspectionDao(YearlyInspectionDao yearlyInspectionDao) {
        this.yearlyInspectionDao = yearlyInspectionDao;
    }

    /**
     * 根据条件查询年检记录
     *
     * @param yearlyInspectionParam 事故查询参数
     * @param pageParam             分页参数
     * @return 年检记录分页对象
     */
    public Page<YearlyInspection> getList(YearlyInspectionParam yearlyInspectionParam, PageParam pageParam) {
        Pageable pageable = pageParam.toPageRequest();

        //若查询参数为空则查询所有事故记录
        if (yearlyInspectionParam == null) {
            return yearlyInspectionDao.findAll(pageable);
        }

        //动态添加查询参数，只有当查询参数不为空时才添加到查询条件里
        Specification<YearlyInspection> specification = (Specification<YearlyInspection>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();

            Long userId = yearlyInspectionParam.getUserId();
            if (userId != null) {
                Path<Long> userIdPath = root.get("userId");
                predicateList.add(criteriaBuilder.equal(userIdPath, userId));
            }

            Path<Date> datePath = root.get("date");
            Date minDate = yearlyInspectionParam.getMinDate();
            if (minDate != null) {
                predicateList.add(criteriaBuilder.greaterThanOrEqualTo(datePath, minDate));
            }
            Date maxDate = yearlyInspectionParam.getMaxDate();
            if (maxDate != null) {
                predicateList.add(criteriaBuilder.lessThanOrEqualTo(datePath, maxDate));
            }

            int predicateSize = predicateList.size();
            Predicate[] predicateArray = predicateList.toArray(new Predicate[predicateSize]);
            return criteriaBuilder.and(predicateArray);
        };

        return yearlyInspectionDao.findAll(specification, pageable);
    }

    /**
     * 保存或更新一条年检记录
     *
     * @param yearlyInspection 年检记录对象
     */
    public void update(YearlyInspection yearlyInspection) {
        yearlyInspectionDao.saveAndFlush(yearlyInspection);
    }

    /**
     * 根据id删除年检记录
     *
     * @param id 年检记录id
     */
    public void deleteById(Long id) {
        yearlyInspectionDao.deleteById(id);
    }

    /**
     * 根据用户id
     *
     * @param userId 用户id
     * @return 事故记录集合
     */
    public List<YearlyInspection> findByUserId(Long userId) {
        return yearlyInspectionDao.findByUserIdOrderByNextDateDesc(userId);
    }

    /**
     * 根据用户id判断是否即将到达年检期限（一个月内需要进行车辆年检）
     *
     * @param userId 用户id
     * @return 最近一次年检记录
     */
    public YearlyInspection isExpire(Long userId) {
        List<YearlyInspection> yearlyInspectionList = yearlyInspectionDao.findByUserIdOrderByNextDateDesc(userId);
        if (yearlyInspectionList.isEmpty()) {
            return null;
        }

        YearlyInspection yearlyInspection = yearlyInspectionList.get(0);
        Date nextDate = yearlyInspection.getNextDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(nextDate);
        Instant instant = calendar.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDate nextLocalDate = instant.atZone(zoneId).toLocalDate();
        LocalDate nowLocalDate = LocalDate.now();

        Period period = Period.between(nowLocalDate, nextLocalDate);
        int months = period.getMonths();
        if (months < 1) {
            return yearlyInspection;
        } else {
            return null;
        }
    }
}
