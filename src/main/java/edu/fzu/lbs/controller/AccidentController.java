package edu.fzu.lbs.controller;

import edu.fzu.lbs.entity.dto.ResultDTO;
import edu.fzu.lbs.entity.param.AccidentParam;
import edu.fzu.lbs.entity.param.PageParam;
import edu.fzu.lbs.entity.po.Accident;
import edu.fzu.lbs.service.AccidentService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Api(tags = "事故记录")
@RestController
@RequestMapping("/accident")
public class AccidentController {
    private AccidentService accidentService;

    @Autowired
    public void setAccidentService(AccidentService accidentService) {
        this.accidentService = accidentService;
    }

    /**
     * 完成由表单到JavaBean属性的绑定
     * 日期参数格式化配置
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @GetMapping
    public ResultDTO<List<Accident>> query(AccidentParam accidentParam, PageParam pageParam) {
        Page<Accident> page = accidentService.getList(accidentParam, pageParam);
        return new ResultDTO<>(page.getContent(), page.getNumber(), page.getTotalPages(), page.getTotalElements());
    }

    @PutMapping
    public ResultDTO put(@RequestBody @Valid Accident accident) {
        accidentService.update(accident);
        return new ResultDTO();
    }

    @DeleteMapping("/{id}")
    public ResultDTO delete(@PathVariable Long id) {
        accidentService.deleteById(id);
        return new ResultDTO();
    }

}
