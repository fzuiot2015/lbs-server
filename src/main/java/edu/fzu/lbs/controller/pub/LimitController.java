package edu.fzu.lbs.controller.pub;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import edu.fzu.lbs.entity.dto.ResultDTO;
import edu.fzu.lbs.entity.po.City;
import edu.fzu.lbs.entity.po.Limit;
import edu.fzu.lbs.util.Constant;
import edu.fzu.lbs.util.HttpGetUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 尾号限行接口
 */
@RestController
@RequestMapping("/limit")
public class LimitController {
    /**
     * 获取接口支持的城市参数集合
     *
     * @return 接口支持的城市参数集合 Map的Key为城市名称的拼音，value为城市名称
     */
    @GetMapping("/city")
    public ResultDTO<Map<String, String>> city() throws IOException {
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

    /**
     * 查询尾号限行信息
     *
     * @param city 城市（名称的拼音）
     * @param date 日期（默认为今天）
     * @return 尾号限行信息集合
     */
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
        return new ResultDTO<>(resultDTO.getResult());
    }
}
