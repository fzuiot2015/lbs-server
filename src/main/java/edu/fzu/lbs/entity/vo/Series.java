package edu.fzu.lbs.entity.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * Echart数据
 */
@Accessors(chain = true)
@Data
public class Series {
    /**
     * 数据名称（横坐标）
     */
    private String name;

    /**
     * 类型
     */
    private String type = "bar";

    private String stack = "价格";

    /**
     * 数据
     */
    private List<Float> data;
}
