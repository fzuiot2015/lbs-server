package edu.fzu.lbs.controller;

import edu.fzu.lbs.entity.dto.ResultDTO;
import edu.fzu.lbs.service.LimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/remind")
public class RemindController {

    private LimitService limitService;

    @Autowired
    public void setLimitService(LimitService limitService) {
        this.limitService = limitService;
    }

    /**
     * 判断指定车牌在指定城市是否限行
     *
     * @param city  所在城市
     * @param plate 车牌号
     * @return 若限行则返回true，否则返回false
     */
    @GetMapping("/limit")
    public ResultDTO<Boolean> isLimit(@RequestParam String city,
                                      @RequestParam String plate) {
        Boolean limited = limitService.isLimited(city, plate);
        return new ResultDTO<>(limited);
    }
}
