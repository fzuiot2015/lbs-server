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

/**
 * 事故记录Service
 */
@Service
public class AccidentService {
    private AccidentDao accidentDao;

    @Autowired
    public void setAccidentDao(AccidentDao accidentDao) {
        this.accidentDao = accidentDao;
    }

    /**
     * 根据条件查询事故记录
     *
     * @param accidentParam 事故查询参数
     * @param pageParam     分页参数
     * @return 事故记录分页对象
     */
    public Page<Accident> getList(AccidentParam accidentParam, PageParam pageParam) {
        Pageable pageable = pageParam.toPageRequest();

        //若查询参数为空则查询所有事故记录
        if (accidentParam == null) {
            return accidentDao.findAll(pageable);
        }

        //动态添加查询参数，只有当查询参数不为空时才添加到查询条件里
        Specification<Accident> specification = (Specification<Accident>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();

            Long userId = accidentParam.getUserId();
            if (userId != null) {
                Path<Long> userIdPath = root.get("userId");
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

    /**
     * 保存或更新一条事故记录
     *
     * @param accident 事故记录对象
     */
    public void update(Accident accident) {
        accidentDao.saveAndFlush(accident);
    }

    /**
     * 根据id删除事故记录
     *
     * @param id 事故记录id
     */
    public void deleteById(Long id) {
        accidentDao.deleteById(id);
    }

    /**
     * 根据用户id
     *
     * @param userId 用户id
     * @return 事故记录集合
     */
    public List<Accident> findByUserId(Long userId) {
        return accidentDao.findByUserIdOrderByTimeDesc(userId);
    }

}
