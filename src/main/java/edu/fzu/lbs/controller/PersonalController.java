package edu.fzu.lbs.controller;

import edu.fzu.lbs.entity.dto.ResultDTO;
import edu.fzu.lbs.entity.po.Accident;
import edu.fzu.lbs.entity.po.Car;
import edu.fzu.lbs.entity.po.Insurance;
import edu.fzu.lbs.entity.po.User;
import edu.fzu.lbs.service.AccidentService;
import edu.fzu.lbs.service.CarService;
import edu.fzu.lbs.service.InsuranceService;
import edu.fzu.lbs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * 用户个人信息接口
 */
@RestController
@RequestMapping("/personal")
public class PersonalController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    private CarService carService;

    @Autowired
    public void setCarService(CarService carService) {
        this.carService = carService;
    }

    private InsuranceService insuranceService;

    @Autowired
    public void setInsuranceService(InsuranceService insuranceService) {
        this.insuranceService = insuranceService;
    }

    private AccidentService accidentService;

    @Autowired
    public void setAccidentService(AccidentService accidentService) {
        this.accidentService = accidentService;
    }

    /**
     * 获取用户个人信息
     *
     * @param userId 用户id
     * @return 用户信息
     */
    @GetMapping("/user")
    public ResultDTO<User> getUserInfo(Long userId) {
        User user = userService.getById(userId);
        return new ResultDTO<>(user);
    }

    /**
     * 获取车辆信息
     *
     * @param userId 用户id
     * @return 车辆信息
     */
    @GetMapping("/car")
    public ResultDTO<List<Car>> getCarInfo(Long userId) {
        List<Car> carList = carService.findByUserId(userId);
        return new ResultDTO<>(carList);
    }

    /**
     * 获取保险记录
     *
     * @param userId 用户id
     * @return 保险记录
     */
    @GetMapping("/insurance")
    public ResultDTO<List<Insurance>> getInsuranceInfo(Long userId) {
        List<Insurance> insuranceList = insuranceService.findByUserId(userId);
        return new ResultDTO<>(insuranceList);
    }

    /**
     * 获取事故记录
     *
     * @param userId 用户id
     * @return 事故记录
     */
    @GetMapping("/accident")
    public ResultDTO<List<Accident>> getAccident(Long userId) {
        List<Accident> accidentList = accidentService.findByUserId(userId);
        return new ResultDTO<>(accidentList);
    }

    /**
     * 提交一条事故记录
     *
     * @return
     */
    @PostMapping("/accident")
    public ResultDTO put(@RequestParam Long userId,
                         Date time,
                         Float lat,
                         Float lng,
                         String address,
                         String description,
                         String photoUrl) {
        if (time == null) {
            time = new Date();
        }
        Accident accident = new Accident();
        accident.setUserId(userId);
        accident.setTime(time);
        accident.setLat(lat);
        accident.setLng(lng);
        accident.setAddress(address);
        accident.setDescription(description);
        accident.setPhotoUrl(photoUrl);
        accidentService.update(accident);
        return new ResultDTO();
    }

}
