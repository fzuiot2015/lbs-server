package edu.fzu.lbs.controller;

import edu.fzu.lbs.dao.InsuranceCompanyDao;
import edu.fzu.lbs.entity.dto.ResultDTO;
import edu.fzu.lbs.entity.po.InsuranceCompany;
import edu.fzu.lbs.entity.vo.Series;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/analyse")
@RestController
public class AnalyseController {

    private InsuranceCompanyDao insuranceCompanyDao;

    @Autowired
    public void setInsuranceCompanyDao(InsuranceCompanyDao insuranceCompanyDao) {
        this.insuranceCompanyDao = insuranceCompanyDao;
    }

    @RequestMapping
    public ResultDTO<List<Series>> analyse() {
        Float price = 500000F;
        List<Series> seriesList = new ArrayList<>();

        List<InsuranceCompany> insuranceCompanies = insuranceCompanyDao.findAll();

        List<Float> lossList = new ArrayList<>();
        List<Float> burglaryList = new ArrayList<>();
        List<Float> personList = new ArrayList<>();

        for (InsuranceCompany insuranceCompany : insuranceCompanies) {
            lossList.add(insuranceCompany.getLoss() * price);
            burglaryList.add(insuranceCompany.getBurglary() * price);
            personList.add(insuranceCompany.getPerson()*3);
        }

        Series loss = new Series()
                .setName("车辆损失险")
                .setData(lossList);

        Series burglary = new Series()
                .setName("全车盗抢险")
                .setData(burglaryList);

        Series person = new Series()
                .setName("车上人员责任险")
                .setData(personList);

        seriesList.add(loss);
        seriesList.add(burglary);
        seriesList.add(person);
        return new ResultDTO<>(seriesList);
    }
}
