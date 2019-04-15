package edu.fzu.lbs.service;

import edu.fzu.lbs.dao.LimitRuleDao;
import edu.fzu.lbs.entity.dto.LimitMsg;
import edu.fzu.lbs.entity.po.LimitRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 限行Service
 */
@Service
public class LimitService {

    private LimitRuleDao limitRuleDao;

    @Autowired
    public void setLimitRuleDao(LimitRuleDao limitRuleDao) {
        this.limitRuleDao = limitRuleDao;
    }

    /**
     * 判断指定车牌在指定城市是否限行
     *
     * @param city  所在城市
     * @param plate 车牌号
     * @return 若限行则返回true，否则返回false
     */
    public LimitMsg isLimited(String city, String plate) {
        LimitRule limitRule = limitRuleDao.findLimitRuleByCity(city);
        String platePrefix = limitRule.getPlatePrefix();
        String limitSuffixStr = limitRule.getLimitSuffix();
        if (!plate.startsWith(platePrefix)) {
            return new LimitMsg(true, limitSuffixStr);
        }
        String[] limitSuffix = limitSuffixStr.split(",");
        for (String suffix : limitSuffix) {
            if (plate.endsWith(suffix)) {
                return new LimitMsg(true, limitSuffixStr);
            }
        }
        return new LimitMsg(false, limitSuffixStr);
    }

}
