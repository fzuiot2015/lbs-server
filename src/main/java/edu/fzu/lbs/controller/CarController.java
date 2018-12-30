package edu.fzu.lbs.controller;

import edu.fzu.lbs.entity.dto.ResultDTO;
import edu.fzu.lbs.entity.param.PageParam;
import edu.fzu.lbs.entity.po.Car;
import edu.fzu.lbs.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 车辆信息接口
 */
@RestController
@RequestMapping("/car")
public class CarController {

    private CarService carService;

    @Autowired
    public void setCarService(CarService carService) {
        this.carService = carService;
    }

    /**
     * 根据车主用户id查询车辆信息
     *
     * @param userId 车主用户id
     * @return 车辆信息集合
     */
    @GetMapping("/own")
    public ResultDTO<List<Car>> findByOwner(@RequestParam Long userId) {
        return new ResultDTO<>(carService.findByUserId(userId));
    }

    /**
     * 查询车辆信息
     *
     * @param car       车辆参数
     * @param pageParam 分页参数
     * @return 车辆信息集合
     */
    @GetMapping
    public ResultDTO<List<Car>> get(Car car, PageParam pageParam) {
        Page<Car> page = carService.getList(car, pageParam);
        return new ResultDTO<>(page.getContent(), page.getNumber(), page.getTotalPages(), page.getTotalElements());
    }

    /**
     * 保存或更新一条车辆信息
     *
     * @param car 车辆对象
     * @return
     */
    @PutMapping
    public ResultDTO put(@RequestBody Car car) {
        carService.update(car);
        return new ResultDTO();
    }

    /**
     * 删除一条车辆对象
     *
     * @param id 车辆id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResultDTO delete(@PathVariable Long id) {
        carService.deleteById(id);
        return new ResultDTO();
    }
}
