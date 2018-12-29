package edu.fzu.lbs.entity.po;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class Limit {
    private String city;        //城市代号
    @SerializedName("cityname")
    private String cityName;    //城市名称
    private String date;        //日期
    private String week;        //星期
    private List<String> time;  //限行时间
    private String area;        //限行区域
    private String summary;     //限行摘要
    @SerializedName("numberrule")
    private String numberRule;  //限行尾号
    private String number;      //尾号规则

}
