package edu.fzu.lbs.service;

import edu.fzu.lbs.dao.AccidentDao;
import edu.fzu.lbs.entity.param.AccidentParam;
import edu.fzu.lbs.entity.param.PageParam;
import edu.fzu.lbs.entity.po.Accident;
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
public class AccidentService implements CrudService<Accident, AccidentParam> {
    private AccidentDao accidentDao;

    @Autowired
    public void setAccidentDao(AccidentDao accidentDao) {
        this.accidentDao = accidentDao;
    }

    public Page<Accident> getList(AccidentParam accidentParam, PageParam pageParam) {
        Pageable pageable = pageParam.toPageRequest();

        if (accidentParam == null) {
            return accidentDao.findAll(pageable);
        }

        //动态添加查询参数
        Specification<Accident> specification = (Specification<Accident>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();

            Path<Long> userIdPath = root.get("userId");
            Long userId = accidentParam.getUserId();
            if (userId != null) {
                predicateList.add(criteriaBuilder.equal(userIdPath, userId));
            }

            Path<Date> timePath = root.get("time");
            Date minTime = accidentParam.getMinTime();
            if (minTime != null) {
                predicateList.add(criteriaBuilder.greaterThanOrEqualTo(timePath, minTime));
            }
            Date maxTime = accidentParam.getMaxTime();
            if (maxTime != null) {
                predicateList.add(criteriaBuilder.lessThanOrEqualTo(timePath, maxTime));
            }

            int predicateSize = predicateList.size();
            Predicate[] predicateArray = predicateList.toArray(new Predicate[predicateSize]);
            return criteriaBuilder.and(predicateArray);
        };

        return accidentDao.findAll(specification, pageable);
    }

    public void update(Accident accident) {
        accidentDao.saveAndFlush(accident);
    }

    public void deleteById(Long id) {
        accidentDao.deleteById(id);
    }

    public List<Accident> findByUserId(Long userId){
        return accidentDao.findByUserId(userId);
    }

}
