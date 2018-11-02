package edu.fzu.lbs.controller.pub;

import edu.fzu.lbs.entity.dto.ResultDTO;
import edu.fzu.lbs.util.HttpGetUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "违章高发地", description = "违章高发地接口，数据来源聚合数据")
@RestController
@RequestMapping("/violation")
public class ViolationPlaceController {

    @ApiOperation(value = "违章高发地查询", notes = "通过百度地图坐标检索周边违章高发地")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "lat", value = "经度", example = "31.335005", required = true),
            @ApiImplicitParam(name = "lon", value = "纬度", example = "120.617183", required = true)
    })
    @GetMapping("/place")
    public ResultDTO get(@RequestParam String lat,
                         @RequestParam String lon) throws Exception {
        HttpGetUtil httpGetUtil = new HttpGetUtil("http://v.juhe.cn/wzpoints/query")
                .addParam("key", "fcaf6167df6f9d6d5aa36a9fb68c7e14")
                .addParam("lat", lat)
                .addParam("lon", lon);
        //TODO:状态码，分页等非必要参数
        return new ResultDTO(httpGetUtil.get().getResult());
    }
}
