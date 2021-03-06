package edu.fzu.lbs.controller;

import edu.fzu.lbs.entity.dto.ResultDTO;
import edu.fzu.lbs.entity.param.InsuranceParam;
import edu.fzu.lbs.entity.param.PageParam;
import edu.fzu.lbs.entity.po.Insurance;
import edu.fzu.lbs.service.InsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 保险记录接口
 */
@RestController
@RequestMapping("/insurance")
public class InsuranceController {
    private InsuranceService insuranceService;

    @Autowired
    public void setInsuranceService(InsuranceService insuranceService) {
        this.insuranceService = insuranceService;
    }

    /**
     * 查询保险信息
     *
     * @param insuranceParam 保险信息查询参数
     * @param pageParam      分页参数
     * @return 保险信息集合
     */
    @GetMapping
    public ResultDTO<List<Insurance>> get(InsuranceParam insuranceParam, PageParam pageParam) {
        Page<Insurance> page = insuranceService.getList(insuranceParam, pageParam);
        return new ResultDTO<>(page.getContent(), page.getNumber(), page.getTotalPages(), page.getTotalElements());
    }

    /**
     * 保存或更新一条保险记录
     *
     * @param insurance 保险记录对象
     * @return
     */
    @PostMapping
    public ResultDTO put(@RequestBody @Valid Insurance insurance) {
        insuranceService.update(insurance);
        return new ResultDTO();
    }

    /**
     * 删除一条保险记录对象
     *
     * @param id 保险记录id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResultDTO delete(@PathVariable Long id) {
        insuranceService.deleteById(id);
        return new ResultDTO();
    }

    /**
     * 根据用户id判断保险是否到期或即将到期（一个月内到期）
     *
     * @param userId 用户id
     * @return 到期或即将到期的保险记录，若保险未到期或即将到期则返回null
     */
    @GetMapping("/expire")
    public ResultDTO<Insurance> expire(Long userId) {
        Insurance insurance = insuranceService.isExpire(userId);
        return new ResultDTO<>(insurance);
    }
}
