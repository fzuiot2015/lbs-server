package edu.fzu.lbs.controller;

import edu.fzu.lbs.entity.dto.ResultDTO;
import edu.fzu.lbs.entity.param.PageParam;
import edu.fzu.lbs.entity.param.YearlyInspectionParam;
import edu.fzu.lbs.entity.po.YearlyInspection;
import edu.fzu.lbs.service.YearlyInspectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 年检记录接口
 */
@RestController
@RequestMapping("/yearly")
public class YearlyInspectionController {
    private YearlyInspectionService yearlyInspectionService;

    @Autowired
    public void setYearlyInspectionService(YearlyInspectionService yearlyInspectionService) {
        this.yearlyInspectionService = yearlyInspectionService;
    }

    /**
     * 根据条件查询年检记录集合
     *
     * @param yearlyInspectionParam 年检记录查询参数
     * @param pageParam             分页参数
     * @return 年检记录集合
     */
    @GetMapping
    public ResultDTO<List<YearlyInspection>> get(YearlyInspectionParam yearlyInspectionParam, PageParam pageParam) {
        Page<YearlyInspection> page = yearlyInspectionService.getList(yearlyInspectionParam, pageParam);
        return new ResultDTO<>(page.getContent(), page.getNumber(), page.getTotalPages(), page.getTotalElements());
    }

    /**
     * 保存或更新一条年检记录
     *
     * @param yearlyInspection 年检记录对象
     * @return
     */
    @PutMapping
    public ResultDTO put(@RequestBody @Valid YearlyInspection yearlyInspection) {
        yearlyInspectionService.update(yearlyInspection);
        return new ResultDTO();
    }

    /**
     * 根据id删除一条年检记录
     *
     * @param id 年检记录id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResultDTO delete(@PathVariable Long id) {
        yearlyInspectionService.deleteById(id);
        return new ResultDTO();
    }


    /**
     * 根据用户id判断是否即将到达年检期限（一个月内需要进行车辆年检）
     *
     * @param userId 用户id
     * @return 最近一次年检记录
     */
    @GetMapping("/expire")
    public ResultDTO<YearlyInspection> expire(Long userId) {
        YearlyInspection yearlyInspection = yearlyInspectionService.isExpire(userId);
        return new ResultDTO<>(yearlyInspection);
    }
}
