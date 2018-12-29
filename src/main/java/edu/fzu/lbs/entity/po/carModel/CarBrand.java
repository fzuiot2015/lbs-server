package edu.fzu.lbs.entity.po.carModel;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class CarBrand {
    private String id;
    private String name;
    private String initial;
    @SerializedName(value = "parentId")
    private String parentid;
    private String logo;
    private String depth;
}
