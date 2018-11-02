package edu.fzu.lbs.controller;

import edu.fzu.lbs.entity.dto.ResultDTO;
import edu.fzu.lbs.entity.param.PageParam;
import edu.fzu.lbs.entity.po.Car;
import edu.fzu.lbs.service.CarService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "车辆", description = "车辆接口")
@RestController
@RequestMapping("/car")
public class CarController {

    private CarService carService;

    @Autowired
    public void setCarService(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/own")
    public ResultDTO<List<Car>> findByOwner(@RequestParam Long userId) {
        return new ResultDTO<>(carService.findByUserId(userId));
    }

    @PutMapping("/register")
    public ResultDTO addCar(@RequestParam Long userId,
                            @RequestParam String vin,
                            @RequestParam String plate,
                            @RequestParam String vehicleType,
                            @RequestParam String engine,
                            @RequestParam String model) {
        //TODO
        Car car = new Car();
        car.setUserId(userId);
        car.setVin(vin);
        car.setPlate(plate);
        car.setVehicleType(vehicleType);
        car.setEngine(engine);
        car.setModel(model);
        return new ResultDTO<>();
    }

    @GetMapping
    public ResultDTO<List<Car>> query(Car car, PageParam pageParam) {
        Page<Car> page = carService.getList(car, pageParam);
        return new ResultDTO<>(page.getContent(), page.getNumber(), page.getTotalPages(), page.getTotalElements());
    }

    @PutMapping
    public ResultDTO put(@RequestBody Car car) {
        carService.update(car);
        return new ResultDTO();
    }

    @DeleteMapping("/{id}")
    public ResultDTO delete(@PathVariable Long id) {
        carService.deleteById(id);
        return new ResultDTO();
    }

}
