package edu.fzu.lbs.controller;

import edu.fzu.lbs.entity.dto.ResultDTO;
import edu.fzu.lbs.entity.param.InsuranceParam;
import edu.fzu.lbs.entity.param.PageParam;
import edu.fzu.lbs.entity.po.Insurance;
import edu.fzu.lbs.service.InsuranceService;
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

@Api(tags = "保险记录")
@RestController
@RequestMapping("/insurance")
public class InsuranceController {
    private InsuranceService insuranceService;

    @Autowired
    public void setInsuranceService(InsuranceService insuranceService) {
        this.insuranceService = insuranceService;
    }

    @GetMapping
    public ResultDTO<List<Insurance>> query(InsuranceParam insuranceParam, PageParam pageParam) {
        Page<Insurance> page = insuranceService.getList(insuranceParam, pageParam);
        return new ResultDTO<>(page.getContent(), page.getNumber(), page.getTotalPages(), page.getTotalElements());
    }

    @PutMapping
    public ResultDTO put(@RequestBody @Valid Insurance insurance) {
        insuranceService.update(insurance);
        return new ResultDTO();
    }

    @DeleteMapping("/{id}")
    public ResultDTO delete(@PathVariable Long id) {
        insuranceService.deleteById(id);
        return new ResultDTO();
    }
}
