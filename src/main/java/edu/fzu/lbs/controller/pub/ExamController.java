package edu.fzu.lbs.controller.pub;

import edu.fzu.lbs.dao.ExamDao;
import edu.fzu.lbs.entity.dto.ResultDTO;
import edu.fzu.lbs.entity.param.PageParam;
import edu.fzu.lbs.entity.po.Exam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 驾照考题接口
 */
@RestController
@RequestMapping("/exam")
public class ExamController {

    private ExamDao examDao;

    @Autowired
    public ExamController(ExamDao examDao) {
        this.examDao = examDao;
    }

    /**
     * 根据条件获取考题集合
     *
     * @param subject   科目类别 [1,4]
     * @param type      驾驶证类型 [c1,c2,a1,a2,b1,b2]
     * @param pageParam 分页参数
     * @return 考题集合
     */
    @GetMapping("/list")
    public ResultDTO<List<Exam>> list(@RequestParam String subject,
                                      @RequestParam String type,
                                      PageParam pageParam) {
        Pageable pageable = pageParam.toPageRequest();
        Page<Exam> pageRes = examDao.findBySubjectAndType(subject, type, pageable);
        return new ResultDTO<>(pageRes.getContent(), pageRes.getNumber(), pageRes.getTotalPages(), pageRes.getTotalElements());
    }

    /**
     * 根据条件获取随机考题
     *
     * @param subject 科目类别 [1,4]
     * @param type    驾驶证类型 [c1,c2,a1,a2,b1,b2]
     * @return 考题对象
     */
    @GetMapping
    public ResultDTO<Exam> get(@RequestParam String subject,
                               @RequestParam String type) {
        return new ResultDTO<>(examDao.get(subject, type));
    }

}
