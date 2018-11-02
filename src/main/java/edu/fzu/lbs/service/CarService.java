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

@Service
public class CarService implements CrudService<Car, Car> {
    private CarDao carDao;

    @Autowired
    public void setCarDao(CarDao carDao) {
        this.carDao = carDao;
    }

    @Override
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

    @Override
    public void update(Car car) {
        carDao.saveAndFlush(car);
    }

    @Override
    public void deleteById(Long id) {
        carDao.deleteById(id);
    }

    public List<Car> findByUserId(Long userId) {
        return carDao.findByUserId(userId);
    }
}
