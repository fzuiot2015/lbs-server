package edu.fzu.lbs.controller.pub;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import edu.fzu.lbs.entity.dto.ResultDTO;
import edu.fzu.lbs.entity.po.City;
import edu.fzu.lbs.entity.po.Limit;
import edu.fzu.lbs.util.Constant;
import edu.fzu.lbs.util.HttpGetUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Api(tags = "尾号限行", description = "尾号限行接口，数据来源聚合数据")
@RestController
@RequestMapping("/limit")
public class LimitController {


    @ApiOperation(value = "城市列表", notes = "获取支持的城市列表")
    @GetMapping("/city")
    public ResultDTO<Map<String, String>> city() throws Exception {
        HttpGetUtil httpGetUtil = new HttpGetUtil("http://api.jisuapi.com/vehiclelimit/city")
                .addParam("appkey", Constant.APP_KEY_JISU);
        String json = httpGetUtil.getJson();
        Gson gson = new Gson();
        Type type = new TypeToken<ResultDTO<List<City>>>() {
        }.getType();
        ResultDTO<List<City>> resultDTO = gson.fromJson(json, type);
        List<City> cityList = resultDTO.getResult();
        Map<String, String> map = cityList.stream().collect(Collectors.toMap(City::getCity, City::getCityName));
        return new ResultDTO<>(map);
    }

    @GetMapping
    public ResultDTO<List<Limit>> get(@RequestParam String city,
                                      String date) throws Exception {
        HttpGetUtil httpGetUtil = new HttpGetUtil("http://api.jisuapi.com/vehiclelimit/query")
                .addParam("appkey", Constant.APP_KEY_JISU)
                .addParam("city", city)
                .addParam("date", date);
        String json = httpGetUtil.getJson();
        Gson gson = new Gson();
        Type type = new TypeToken<ResultDTO<Limit>>() {
        }.getType();
        ResultDTO<List<Limit>> resultDTO = gson.fromJson(json, type);
        return new ResultDTO(resultDTO.getResult());
    }

}
