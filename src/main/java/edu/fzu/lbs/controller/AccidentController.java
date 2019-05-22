package edu.fzu.lbs.controller;

import edu.fzu.lbs.entity.dto.ResultDTO;
import edu.fzu.lbs.entity.param.AccidentParam;
import edu.fzu.lbs.entity.param.PageParam;
import edu.fzu.lbs.entity.po.Accident;
import edu.fzu.lbs.service.AccidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 事故记录接口
 */
@RestController
@RequestMapping("/accident")
public class AccidentController {
    private AccidentService accidentService;

    @Autowired
    public void setAccidentService(AccidentService accidentService) {
        this.accidentService = accidentService;
    }

    /**
     * 根据条件查询事故记录集合
     *
     * @param accidentParam 事故记录查询参数
     * @param pageParam     分页参数
     * @return 事故记录集合
     */
    @GetMapping
    public ResultDTO<List<Accident>> get(AccidentParam accidentParam, PageParam pageParam) {
        Page<Accident> page = accidentService.getList(accidentParam, pageParam);
        return new ResultDTO<>(page.getContent(), page.getNumber(), page.getTotalPages(), page.getTotalElements());
    }

    /**
     * 保存或更新一条事故记录
     *
     * @param accident 事故记录对象
     * @return
     */
    @PostMapping
    public ResultDTO put(@RequestBody @Valid Accident accident) {
        accidentService.update(accident);
        return new ResultDTO();
    }

    /**
     * 根据id删除一条事故记录
     *
     * @param id 事故记录id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResultDTO delete(@PathVariable Long id) {
        accidentService.deleteById(id);
        return new ResultDTO();
    }
}
