package edu.fzu.lbs.controller;

import edu.fzu.lbs.dao.InsuranceCompanyDao;
import edu.fzu.lbs.entity.dto.ResultDTO;
import edu.fzu.lbs.entity.po.InsuranceCompany;
import edu.fzu.lbs.entity.vo.Series;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 保险价格分析接口
 */
@RequestMapping("/analyse")
@RestController
public class AnalyseController {

    private InsuranceCompanyDao insuranceCompanyDao;

    @Autowired
    public void setInsuranceCompanyDao(InsuranceCompanyDao insuranceCompanyDao) {
        this.insuranceCompanyDao = insuranceCompanyDao;
    }

    /**
     * 获取保险价格分析结果
     *
     * @param personNum 车载人数
     * @param price     车辆价格
     * @return Series对象集合，可直接用于Echart的Series字段
     */
    @GetMapping("/insurance")
    public ResultDTO<List<Series>> insuranceAnalyse(Integer personNum, Float price) {
        List<Series> seriesList = new ArrayList<>();
        List<InsuranceCompany> insuranceCompanies = insuranceCompanyDao.findAll();
        List<Float> lossList = new ArrayList<>();
        List<Float> burglaryList = new ArrayList<>();
        List<Float> glassList = new ArrayList<>();
        List<Float> fireList = new ArrayList<>();
        List<Float> personList = new ArrayList<>();

        //遍历保险公司集合分别计算保险价格
        for (InsuranceCompany insuranceCompany : insuranceCompanies) {
            lossList.add(insuranceCompany.getLoss() * price);
            burglaryList.add(insuranceCompany.getBurglary() * price);
            glassList.add(insuranceCompany.getGlass() * price);
            fireList.add(insuranceCompany.getFire() * price);
            personList.add(insuranceCompany.getPerson() * personNum);
        }

        Series loss = new Series()
                .setName("车辆损失险")
                .setData(lossList);

        Series burglary = new Series()
                .setName("全车盗抢险")
                .setData(burglaryList);

        Series glass = new Series()
                .setName("玻璃单独破碎险")
                .setData(glassList);

        Series fire = new Series()
                .setName("自燃损失险")
                .setData(fireList);

        Series person = new Series()
                .setName("车上人员责任险")
                .setData(personList);

        seriesList.add(loss);
        seriesList.add(burglary);
        seriesList.add(glass);
        seriesList.add(fire);
        seriesList.add(person);
        return new ResultDTO<>(seriesList);
    }

    @GetMapping("/behavior")
    public ResultDTO<List<Double>> behaviorAnalyse() {
        List<Double> list = new ArrayList<>();
        //TODO:修改为真实数值
        list.add(73D);
        list.add(1.56);
        list.add(1D);
        list.add(5D);
        list.add(3D);
        list.add(2D);
        return new ResultDTO<>(list);
    }
}
