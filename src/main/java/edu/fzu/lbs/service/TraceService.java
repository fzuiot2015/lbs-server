package edu.fzu.lbs.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import edu.fzu.lbs.dao.DrivingBehaviorDao;
import edu.fzu.lbs.dao.UserDao;
import edu.fzu.lbs.entity.po.DrivingBehavior;
import edu.fzu.lbs.entity.po.User;
import edu.fzu.lbs.util.Constant;
import edu.fzu.lbs.util.HttpGetUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;

/**
 * 百度鹰眼轨迹追踪Service
 */
@Service
public class TraceService {

    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    private DrivingBehaviorDao drivingBehaviorDao;

    @Autowired
    public void setDrivingBehaviorDao(DrivingBehaviorDao drivingBehaviorDao) {
        this.drivingBehaviorDao = drivingBehaviorDao;
    }

    /**
     * 获取最近一周的驾驶行为数据
     *
     * @param entityName 百度鹰眼终端标识
     * @return 最近一周的驾驶行为数据
     */
    public DrivingBehavior behavior(String entityName) {
        Pageable pageable = PageRequest.of(0, 7);
        List<DrivingBehavior> drivingBehaviorList = drivingBehaviorDao.findByEntityNameOrderByDateDesc(entityName, pageable);

        double distance = drivingBehaviorList.stream()
                .mapToDouble(DrivingBehavior::getDistance)
                .sum();

        int duration = drivingBehaviorList.stream()
                .mapToInt(DrivingBehavior::getDuration)
                .sum();

        double averageSpeed = drivingBehaviorList.stream()
                .mapToDouble(DrivingBehavior::getAverageSpeed)
                .average().orElse(0);

        double maxSpeed = drivingBehaviorList.stream().
                mapToDouble(DrivingBehavior::getMaxSpeed)
                .max().orElse(0);

        int speedingNum = drivingBehaviorList.stream()
                .mapToInt(DrivingBehavior::getSpeedingNum)
                .sum();

        int harshAccelerationNum = drivingBehaviorList.stream()
                .mapToInt(DrivingBehavior::getHarshAccelerationNum)
                .sum();

        int harshBreakingNum = drivingBehaviorList.stream()
                .mapToInt(DrivingBehavior::getHarshBreakingNum)
                .sum();

        int harshSteeringNum = drivingBehaviorList.stream()
                .mapToInt(DrivingBehavior::getHarshBreakingNum)
                .sum();

        DrivingBehavior drivingBehavior = new DrivingBehavior(distance, duration, averageSpeed,
                maxSpeed, speedingNum, harshAccelerationNum, harshBreakingNum, harshSteeringNum);

        return drivingBehavior;
    }

    /**
     * 更新数据
     *
     * @throws IOException
     */
    public void updateData() throws IOException {
        ZoneOffset zoneOffset = ZoneOffset.ofHours(8);
        LocalDateTime now = LocalDateTime.now();
        long endTime = now.toEpochSecond(zoneOffset);
        LocalDateTime yesterday = now.minusDays(1);
        long startTime = yesterday.toEpochSecond(zoneOffset);

        List<User> userList = userDao.findAll();
        for (User user : userList) {
            String username = user.getUsername();
            updateOneData(username, startTime, endTime);
        }
    }

    /**
     * 更新一条数据
     *
     * @param entityName 百度鹰眼终端标识
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @throws IOException
     */
    private void updateOneData(String entityName, Long startTime, Long endTime) throws IOException {
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
        if (status == 0) {
            DrivingBehavior drivingBehavior = gson.fromJson(json, DrivingBehavior.class);
            drivingBehavior.setEntityName(entityName);
            drivingBehavior.setDate(new Date());
            drivingBehaviorDao.save(drivingBehavior);
        }
    }
}