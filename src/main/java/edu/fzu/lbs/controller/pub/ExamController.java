package edu.fzu.lbs.controller.pub;

import edu.fzu.lbs.dao.ExamDao;
import edu.fzu.lbs.entity.dto.ResultDTO;
import edu.fzu.lbs.entity.param.PageParam;
import edu.fzu.lbs.entity.po.Exam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "驾照考题", description = "驾照考题接口")
@RestController
@RequestMapping("/exam")
public class ExamController {

    private ExamDao examDao;

    @Autowired
    public ExamController(ExamDao examDao) {
        this.examDao = examDao;
    }

    /**
     * 根据条件查询实体信息
     *
     * @param subject   科目类别
     * @param type      驾驶证类型
     * @param pageParam 分页参数
     */
    @ApiOperation(value = "考题列表", notes = "根据指定的科目类别和驾驶证类型获取驾照考题列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "subject", value = "科目类别[1,4]", example = "4", required = true),
            @ApiImplicitParam(name = "type", value = "驾驶证类型[c1,c2,a1,a2,b1,b2]", example = "c1", required = true),
            @ApiImplicitParam(name = "pageNum", value = "分页页码", defaultValue = "0", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "分页每页内容数", defaultValue = "5", dataType = "int")
    })
    @GetMapping("/list")
    public ResultDTO<List<Exam>> all(@RequestParam String subject,
                                     @RequestParam String type,
                                     PageParam pageParam) {
        Pageable pageable = pageParam.toPageRequest();
        Page<Exam> pageRes = examDao.findBySubjectAndType(subject, type, pageable);
        return new ResultDTO<>(pageRes.getContent(), pageRes.getNumber(), pageRes.getTotalPages(), pageRes.getTotalElements());
    }

    @ApiOperation(value = "随机考题", notes = "根据指定的科目类别和驾驶证类型获取一个随机的驾照考题对象")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "subject", value = "科目类别[1,4]", example = "4", required = true),
            @ApiImplicitParam(name = "type", value = "驾驶证类型[c1,c2,a1,a2,b1,b2]", example = "c1", required = true)
    })
    @GetMapping
    public ResultDTO<Exam> get(@RequestParam String subject,
                               @RequestParam String type) {
        return new ResultDTO<>(examDao.get(subject, type));
    }

}
