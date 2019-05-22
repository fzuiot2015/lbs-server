package edu.fzu.lbs.controller;

import edu.fzu.lbs.entity.dto.ResultDTO;
import edu.fzu.lbs.entity.param.AccidentParam;
import edu.fzu.lbs.entity.param.PageParam;
import edu.fzu.lbs.entity.param.ViolationParam;
import edu.fzu.lbs.entity.po.Accident;
import edu.fzu.lbs.entity.po.Violation;
import edu.fzu.lbs.service.ViolationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 违章记录接口
 */
@RestController
@RequestMapping("/violation")
public class ViolationController {
    private ViolationService violationService;

    @Autowired
    public void setViolationService(ViolationService violationService) {
        this.violationService = violationService;
    }

    /**
     * 根据条件查询违章记录集合
     *
     * @param violationParam 事故记录查询参数
     * @param pageParam     分页参数
     * @return 事故记录集合
     */
    @GetMapping
    public ResultDTO<List<Violation>> get(ViolationParam violationParam, PageParam pageParam) {
        Page<Violation> page = violationService.getList(violationParam, pageParam);
        return new ResultDTO<>(page.getContent(), page.getNumber(), page.getTotalPages(), page.getTotalElements());
    }

    /**
     * 保存或更新一条违章记录
     *
     * @param violation 违章记录对象
     * @return
     */
    @PutMapping
    public ResultDTO put(@RequestBody @Valid Violation violation) {
        violationService.update(violation);
        return new ResultDTO();
    }

    /**
     * 根据id删除一条违章记录
     *
     * @param id 违章记录id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResultDTO delete(@PathVariable Long id) {
        violationService.deleteById(id);
        return new ResultDTO();
    }
}
