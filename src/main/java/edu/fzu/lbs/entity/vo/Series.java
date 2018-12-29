package edu.fzu.lbs.entity.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Accessors(chain = true)
@Data
public class Series {
    private String name;
    private String type = "bar";
    private String stack = "价格";
    private List<Float> data;
}
