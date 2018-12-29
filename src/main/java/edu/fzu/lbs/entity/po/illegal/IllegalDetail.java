package edu.fzu.lbs.entity.po.illegal;

import lombok.Data;

@Data
public class IllegalDetail {
    private String id;
    private String time;
    private String address;
    private String content;     //违章内容
    private String legalnum;    //违章代码
    private String price;       //罚款金额
    private String score;       //扣分
}
