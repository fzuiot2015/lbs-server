package edu.fzu.lbs.controller.pub;

import edu.fzu.lbs.dao.TipsDao;
import edu.fzu.lbs.entity.dto.ResultDTO;
import edu.fzu.lbs.entity.po.Tips;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tips")
public class TipsController {

    private TipsDao tipsDao;

    @Autowired
    public void setTipsDao(TipsDao tipsDao) {
        this.tipsDao = tipsDao;
    }

    @GetMapping
    public ResultDTO<List<Tips>> get() {
        List<Tips> tipsList = tipsDao.findAll();
        return new ResultDTO<>(tipsList);
    }
}
