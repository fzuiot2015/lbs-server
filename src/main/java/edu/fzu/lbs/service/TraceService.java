package edu.fzu.lbs.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import edu.fzu.lbs.entity.dto.DrivingBehavior;
import edu.fzu.lbs.entity.dto.ResultDTO;
import edu.fzu.lbs.util.Constant;
import edu.fzu.lbs.util.HttpGetUtil;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * 百度鹰眼轨迹追踪Service
 */
@Service
public class TraceService {

    public ResultDTO<DrivingBehavior> behavior(String entityName) throws IOException {

        ZoneOffset zoneOffset = ZoneOffset.ofHours(8);
        LocalDateTime now = LocalDateTime.now();
        long endTime = now.toEpochSecond(zoneOffset);
        LocalDateTime yesterday = now.minusDays(1);
        long startTime = yesterday.toEpochSecond(zoneOffset);

        HttpGetUtil httpGetUtil = new HttpGetUtil("http://yingyan.baidu.com/api/v3/analysis/drivingbehavior")
                .addParam("ak", Constant.APP_KEY_BAIDU)
                .addParam("service_id", Constant.SERVICE_ID_BAIDU_TRACE)
                .addParam("entity_name", entityName)
                .addParam("start_time", String.valueOf(startTime))
                .addParam("end_time", String.valueOf(endTime));
        String json = httpGetUtil.getJson();
        Gson gson = new Gson();
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
        int status = jsonObject.get("status").getAsInt();
        ResultDTO<DrivingBehavior> resultDTO;
        if (status == 0) {
            DrivingBehavior drivingBehavior = gson.fromJson(json, DrivingBehavior.class);
            resultDTO = new ResultDTO<>(drivingBehavior);
        } else {
            String message = jsonObject.get("message").getAsString();
            resultDTO = ResultDTO.error(status, message);
        }
        return resultDTO;
    }
}