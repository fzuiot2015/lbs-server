package edu.fzu.lbs.controller.pub;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import edu.fzu.lbs.entity.dto.ResultDTO;
import edu.fzu.lbs.entity.po.carModel.CarBrand;
import edu.fzu.lbs.entity.po.carModel.CarType;
import edu.fzu.lbs.util.HttpGetUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import static edu.fzu.lbs.util.Constant.APP_KEY_JISU;

@RestController
@RequestMapping("/model")
public class CarModelController {

    @GetMapping("/brand")
    public ResultDTO brand() throws IOException {
        HttpGetUtil httpGetUtil = new HttpGetUtil("http://api.jisuapi.com/car/brand")
                .addParam("appkey", APP_KEY_JISU);
        String json = httpGetUtil.getJson();
        Gson gson = new Gson();
        Type type = new TypeToken<ResultDTO<List<CarBrand>>>() {
        }.getType();
        ResultDTO resultDTO = gson.fromJson(json, type);
        return resultDTO;
    }

    @GetMapping("/model")
    public ResultDTO model(String parentId) throws IOException {
        HttpGetUtil httpGetUtil = new HttpGetUtil("http://api.jisuapi.com/car/type")
                .addParam("appkey", APP_KEY_JISU)
                .addParam("parentid", parentId);
        String json = httpGetUtil.getJson();
        Gson gson = new Gson();
        Type type = new TypeToken<ResultDTO<List<CarType>>>() {
        }.getType();
        ResultDTO resultDTO = gson.fromJson(json, type);
        return resultDTO;
    }

    @GetMapping("/car")
    public ResultDTO car(String parentId) throws IOException {
        HttpGetUtil httpGetUtil = new HttpGetUtil("http://api.jisuapi.com/car/car")
                .addParam("appkey", APP_KEY_JISU)
                .addParam("parentid", parentId);
        String json = httpGetUtil.getJson();
        Gson gson = new Gson();
        Type type = new TypeToken<ResultDTO<CarType>>() {
        }.getType();
        ResultDTO resultDTO = gson.fromJson(json, type);
        return resultDTO;
    }

    @GetMapping("/detail")
    public ResultDTO detail(String carId) throws IOException {
        HttpGetUtil httpGetUtil = new HttpGetUtil("http://api.jisuapi.com/car/detail")
                .addParam("appkey", APP_KEY_JISU)
                .addParam("carid", carId);
        String json = httpGetUtil.getJson();
        Gson gson = new Gson();
        ResultDTO resultDTO = gson.fromJson(json, ResultDTO.class);
        return resultDTO;
    }
}
