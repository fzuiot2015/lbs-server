package edu.fzu.lbs.entity.param;

import lombok.Data;
import org.springframework.data.domain.PageRequest;

/**
 * 分页参数
 */
@Data
public class PageParam {

    /**
     * 分页页码,默认为0
     */
    private Integer pageNum = 0;

    /**
     * 分页容量,默认为5
     */
    private Integer pageSize = 5;

    /**
     * 转换为PageRequest对象
     *
     * @return PageRequest对象
     */
    public PageRequest toPageRequest() {
        return PageRequest.of(pageNum, pageSize);
    }

}
