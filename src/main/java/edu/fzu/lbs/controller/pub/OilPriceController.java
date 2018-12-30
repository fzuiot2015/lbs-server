package edu.fzu.lbs.controller.pub;

import edu.fzu.lbs.dao.OilPriceDao;
import edu.fzu.lbs.entity.dto.ResultDTO;
import edu.fzu.lbs.entity.po.OilPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 油价查询接口
 */
@RestController
@RequestMapping("/oil")
public class OilPriceController {
    private OilPriceDao oilPriceDao;

    @Autowired
    public void setOilPriceDao(OilPriceDao oilPriceDao) {
        this.oilPriceDao = oilPriceDao;
    }

    /**
     * 根据省份查询油价
     *
     * @param province 省份
     * @return 油价信息
     */
    @GetMapping("/price")
    public ResultDTO<OilPrice> price(@RequestParam String province) {
        return new ResultDTO<>(oilPriceDao.findOilPriceByProvince(province));
    }

    /**
     * 查询接口支持的省份参数
     *
     * @return 支持的省份集合
     */
    @GetMapping("/province")
    public ResultDTO<List<String>> city() {
        return new ResultDTO<>(oilPriceDao.findProvinces());
    }
}
