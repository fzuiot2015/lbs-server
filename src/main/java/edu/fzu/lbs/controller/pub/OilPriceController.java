package edu.fzu.lbs.controller.pub;

import edu.fzu.lbs.dao.OilPriceDao;
import edu.fzu.lbs.entity.dto.ResultDTO;
import edu.fzu.lbs.entity.po.OilPrice;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "油价查询")
@RestController
@RequestMapping("/oil")
public class OilPriceController {

    private OilPriceDao oilPriceDao;

    @Autowired
    public void setOilPriceDao(OilPriceDao oilPriceDao) {
        this.oilPriceDao = oilPriceDao;
    }

    @ApiOperation(value = "油价查询", notes = "根据省份查询油价，支持省份列表可通过/province接口查询")
    @ApiImplicitParam(name = "province", value = "省份", example = "福建", required = true)
    @GetMapping("/price")
    public ResultDTO<OilPrice> oil(@RequestParam String province) {

        return new ResultDTO<>(oilPriceDao.findOilPriceByProvince(province));
    }

    @ApiOperation(value = "获取省份列表", notes = "获取支持油价查询的省份列表")
    @GetMapping("/province")
    public ResultDTO<List<String>> city() {
        return new ResultDTO<>(oilPriceDao.findProvinces());
    }


}
