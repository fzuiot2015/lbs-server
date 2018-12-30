package edu.fzu.lbs.service;

import edu.fzu.lbs.dao.CarDao;
import edu.fzu.lbs.entity.param.PageParam;
import edu.fzu.lbs.entity.po.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 车辆信息Service
 */
@Service
public class CarService {
    private CarDao carDao;

    @Autowired
    public void setCarDao(CarDao carDao) {
        this.carDao = carDao;
    }

    /**
     * 根据条件查询车辆信息
     *
     * @param car       车辆查询参数
     * @param pageParam 分页参数
     * @return 车辆信息分页对象
     */
    public Page<Car> getList(Car car, PageParam pageParam) {
        Pageable pageable = pageParam.toPageRequest();
        Page<Car> page;
        if (car == null) {
            page = carDao.findAll(pageable);
        } else {
            Example<Car> example = Example.of(car);
            page = carDao.findAll(example, pageable);
        }
        return page;
    }

    /**
     * 保存或更新一条车辆信息
     *
     * @param car 车辆信息
     */
    public void update(Car car) {
        carDao.saveAndFlush(car);
    }

    /**
     * 根据id删除车辆信息
     *
     * @param id 车辆id
     */
    public void deleteById(Long id) {
        carDao.deleteById(id);
    }

    /**
     * 根据车主用户id查询车辆信息
     *
     * @param userId 车主用户 id
     * @return 车辆信息集合
     */
    public List<Car> findByUserId(Long userId) {
        return carDao.findByUserId(userId);
    }
}
