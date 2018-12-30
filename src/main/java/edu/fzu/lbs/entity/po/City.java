package edu.fzu.lbs.entity.po;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * 城市对象，用于尾号限行接口
 */
@Data
public class City {
    /**
     * 城市代码（城市名称的拼音）
     */
    private String city;

    /**
     * 城市名
     */
    @SerializedName("cityname")
    private String cityName;
}
