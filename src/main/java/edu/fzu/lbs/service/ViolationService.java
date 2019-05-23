package edu.fzu.lbs.service;

import edu.fzu.lbs.dao.ViolationDao;
import edu.fzu.lbs.entity.param.PageParam;
import edu.fzu.lbs.entity.param.ViolationParam;
import edu.fzu.lbs.entity.po.Violation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 违章记录Service
 */
@Service
public class ViolationService {
    private ViolationDao violationDao;

    @Autowired
    public void setViolationDao(ViolationDao violationDao) {
        this.violationDao = violationDao;
    }

    /**
     * 根据条件查询违章记录
     *
     * @param violationParam 违章查询参数
     * @param pageParam     分页参数
     * @return 违章记录分页对象
     */
    public Page<Violation> getList(ViolationParam violationParam, PageParam pageParam) {
        Pageable pageable = pageParam.toPageRequest();

        //若查询参数为空则查询所有事故记录
        if (violationParam == null) {
            return violationDao.findAll(pageable);
        }

        //动态添加查询参数，只有当查询参数不为空时才添加到查询条件里
        Specification<Violation> specification = (Specification<Violation>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();

            Long userId = violationParam.getUserId();
            if (userId != null) {
                Path<Long> userIdPath = root.get("userId");
                predicateList.add(criteriaBuilder.equal(userIdPath, userId));
            }

            Path<Date> timePath = root.get("time");
            Date minTime = violationParam.getMinTime();
            if (minTime != null) {
                predicateList.add(criteriaBuilder.greaterThanOrEqualTo(timePath, minTime));
            }
            Date maxTime = violationParam.getMaxTime();
            if (maxTime != null) {
                predicateList.add(criteriaBuilder.lessThanOrEqualTo(timePath, maxTime));
            }

            int predicateSize = predicateList.size();
            Predicate[] predicateArray = predicateList.toArray(new Predicate[predicateSize]);
            return criteriaBuilder.and(predicateArray);
        };

        return violationDao.findAll(specification, pageable);
    }

    /**
     * 保存或更新一条违章记录
     *
     * @param violation 违章记录对象
     */
    public void update(Violation violation) {
        violationDao.saveAndFlush(violation);
    }

    /**
     * 根据id删除违章记录
     *
     * @param id 违章记录id
     */
    public void deleteById(Long id) {
        violationDao.deleteById(id);
    }
}
