package edu.fzu.lbs.controller.pub;

import edu.fzu.lbs.dao.CheckStepDao;
import edu.fzu.lbs.entity.dto.ResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/check")
public class CheckStepController {
    private CheckStepDao checkStepDao;

    @Autowired
    public void setCheckStepDao(CheckStepDao checkStepDao) {
        this.checkStepDao = checkStepDao;
    }

    @GetMapping
    public ResultDTO<List<String>> get(int type) {
        List<String> content = checkStepDao.findContent(type);
        return new ResultDTO<>(content);
    }
}
