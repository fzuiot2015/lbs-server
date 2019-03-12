package edu.fzu.lbs.service;

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

    public String behavior(String entityName) throws IOException {

        ZoneOffset zoneOffset = ZoneOffset.ofHours(8);
        LocalDateTime now = LocalDateTime.now();
        long startTime = now.toEpochSecond(zoneOffset);
        LocalDateTime yesterday = now.minusDays(1);
        long endTime = yesterday.toEpochSecond(zoneOffset);

        HttpGetUtil httpGetUtil = new HttpGetUtil("http://yingyan.baidu.com/api/v3/analysis/drivingbehavior")
                .addParam("ak", Constant.APP_KEY_BAIDU)
                .addParam("service_id", Constant.SERVICE_ID_BAIDU_TRACE)
                .addParam("entity_name", entityName)
                .addParam("start_time", String.valueOf(startTime))
                .addParam("end_time", String.valueOf(endTime));
        String json = httpGetUtil.getJson();
        //TODO：转换为数据类
        return json;
    }
}