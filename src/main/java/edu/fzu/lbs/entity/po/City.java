package edu.fzu.lbs.entity.po;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class City {
    private String city;
    @SerializedName("cityname")
    private String cityName;
}
