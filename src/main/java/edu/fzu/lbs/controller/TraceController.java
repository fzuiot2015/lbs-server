package edu.fzu.lbs.controller;


import edu.fzu.lbs.entity.dto.ResultDTO;
import edu.fzu.lbs.entity.param.PageParam;
import edu.fzu.lbs.entity.param.TraceParam;
import edu.fzu.lbs.entity.po.DrivingBehavior;
import edu.fzu.lbs.service.TraceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
 * 百度鹰眼轨迹追踪Controller
 */
@RestController
@RequestMapping("/trace")
public class TraceController {
    private TraceService traceService;

    @Autowired
    public void setTraceService(TraceService traceService) {
        this.traceService = traceService;
    }

    /**
     * 驾驶行为分析
     *
     * @param entityName entity名称，唯一标识
     * @return
     */
    @RequestMapping("/behavior")
    public ResultDTO<DrivingBehavior> behavior(String entityName) {
        DrivingBehavior drivingBehavior = traceService.behavior(entityName);
        return new ResultDTO<>(drivingBehavior);
    }

    @RequestMapping("/update")
    public void update() throws IOException {
        traceService.updateData();
    }

    /**
     * 根据条件查询驾驶行为数据记录集合
     *
     * @param traceParam 驾驶行为查询参数
     * @param pageParam      分页参数
     * @return 违章记录集合
     */
    @GetMapping
    public ResultDTO<List<DrivingBehavior>> get(TraceParam traceParam, PageParam pageParam) {
        Page<DrivingBehavior> page = traceService.getList(traceParam, pageParam);
        return new ResultDTO<>(page.getContent(), page.getNumber(), page.getTotalPages(), page.getTotalElements());
    }

    /**
     * 保存或更新一条驾驶行为记录
     *
     * @param drivingBehavior 驾驶行为记录对象
     * @return
     */
    @PostMapping
    public ResultDTO put(@RequestBody @Valid DrivingBehavior drivingBehavior) {
        traceService.update(drivingBehavior);
        return new ResultDTO();
    }

    /**
     * 根据id删除一条驾驶行为记录
     *
     * @param id 驾驶行为记录id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResultDTO delete(@PathVariable Long id) {
        traceService.deleteById(id);
        return new ResultDTO();
    }
}
