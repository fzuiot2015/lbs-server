package edu.fzu.lbs.entity.po.carModel;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class CarType {
    private String id;
    private String name;
    @SerializedName("fullname")
    private String fullName;
    private String initial;
    private String logo;
    @SerializedName("salestate")
    private String saleState;
    private String depth;
    private List<CarModel> list;
}
