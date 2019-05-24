package edu.fzu.lbs.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import edu.fzu.lbs.dao.DrivingBehaviorDao;
import edu.fzu.lbs.dao.UserDao;
import edu.fzu.lbs.entity.param.PageParam;
import edu.fzu.lbs.entity.param.TraceParam;
import edu.fzu.lbs.entity.po.DrivingBehavior;
import edu.fzu.lbs.entity.po.User;
import edu.fzu.lbs.util.Constant;
import edu.fzu.lbs.util.HttpGetUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
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

        DecimalFormat decimalFormat = new DecimalFormat("#.00");

        double distance = drivingBehaviorList.stream()
                .mapToDouble(DrivingBehavior::getDistance)
                .sum();

        String distanceStr = decimalFormat.format(distance);


        int duration = drivingBehaviorList.stream()
                .mapToInt(DrivingBehavior::getDuration)
                .sum();

        double averageSpeed = drivingBehaviorList.stream()
                .mapToDouble(DrivingBehavior::getAverageSpeed)
                .average().orElse(0);

        String averageSpeedStr = decimalFormat.format(averageSpeed);

        double maxSpeed = drivingBehaviorList.stream().
                mapToDouble(DrivingBehavior::getMaxSpeed)
                .max().orElse(0);

        String maxSpeedStr = decimalFormat.format(maxSpeed);

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

        DrivingBehavior drivingBehavior = new DrivingBehavior(Double.valueOf(distanceStr),
                duration, Double.valueOf(averageSpeedStr), Double.valueOf(maxSpeedStr),
                speedingNum, harshAccelerationNum, harshBreakingNum, harshSteeringNum);

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
     * @param startTime  开始时间
     * @param endTime    结束时间
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

    /**
     * 保存或更新一条记录
     *
     * @param drivingBehavior 驾驶行为记录对象
     */
    public void update(DrivingBehavior drivingBehavior) {
        drivingBehaviorDao.saveAndFlush(drivingBehavior);
    }

    /**
     * 根据id删除驾驶行为记录
     *
     * @param id 驾驶行为记录id
     */
    public void deleteById(Long id) {
        drivingBehaviorDao.deleteById(id);
    }

    /**
     * 根据条件查询驾驶行为记录
     *
     * @param traceParam 驾驶行为查询参数
     * @param pageParam  分页参数
     * @return 驾驶行为记录分页对象
     */
    public Page<DrivingBehavior> getList(TraceParam traceParam, PageParam pageParam) {
        Pageable pageable = pageParam.toPageRequest();

        //若查询参数为空则查询所有事故记录
        if (traceParam == null) {
            return drivingBehaviorDao.findAll(pageable);
        }

        //动态添加查询参数，只有当查询参数不为空时才添加到查询条件里
        Specification<DrivingBehavior> specification = (Specification<DrivingBehavior>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();

            String entityName = traceParam.getEntityName();
            if (entityName != null && entityName.isEmpty()) {
                Path<Long> entityNamePath = root.get("entityName");
                predicateList.add(criteriaBuilder.equal(entityNamePath, entityName));
            }

            Path<Date> datePath = root.get("date");
            Date minTime = traceParam.getMinDate();
            if (minTime != null) {
                predicateList.add(criteriaBuilder.greaterThanOrEqualTo(datePath, minTime));
            }
            Date maxTime = traceParam.getMinDate();
            if (maxTime != null) {
                predicateList.add(criteriaBuilder.lessThanOrEqualTo(datePath, maxTime));
            }

            int predicateSize = predicateList.size();
            Predicate[] predicateArray = predicateList.toArray(new Predicate[predicateSize]);
            return criteriaBuilder.and(predicateArray);
        };

        return drivingBehaviorDao.findAll(specification, pageable);
    }
}