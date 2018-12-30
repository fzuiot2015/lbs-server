package edu.fzu.lbs.entity.po;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class Limit {
    /**
     * 城市代号（城市名称拼音）
     */
    private String city;

    /**
     * 城市名称
     */
    @SerializedName("cityname")
    private String cityName;

    /**
     * 日期
     */
    private String date;

    /**
     * 星期
     */
    private String week;

    /**
     * 限行时间
     */
    private List<String> time;

    /**
     * 限行区域
     */
    private String area;

    /**
     * 限行摘要
     */
    private String summary;

    /**
     * 限行尾号
     */
    @SerializedName("numberrule")
    private String numberRule;

    /**
     * 尾号规则
     */
    private String number;

}
