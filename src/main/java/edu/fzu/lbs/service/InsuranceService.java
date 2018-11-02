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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class InsuranceService implements CrudService<Insurance, InsuranceParam> {
    private InsuranceDao insuranceDao;

    @Autowired
    public void setInsuranceDao(InsuranceDao insuranceDao) {
        this.insuranceDao = insuranceDao;
    }

    public Page<Insurance> getList(InsuranceParam insuranceParam, PageParam pageParam) {
        Pageable pageable = pageParam.toPageRequest();

        //FIXME:NULL判断失效
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

    public void update(Insurance insurance) {
        insuranceDao.saveAndFlush(insurance);
    }

    public void deleteById(Long id) {
        insuranceDao.deleteById(id);
    }

}
