package edu.fzu.lbs.entity.po.carModel;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class CarModel {
    private String id;
    private String name;
    private String logo;
    private String price;
    @SerializedName("yeartype")
    private String yearType;
    @SerializedName("productionstate")
    private String productionState;
}
