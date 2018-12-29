package edu.fzu.lbs.entity.po;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "exam")
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String subject; //科目类别
    private String type;    //驾驶证类型
    private String question;//题目
    private String item1;   //选项1
    private String item2;   //选项2
    private String item3;   //选项3
    private String item4;   //选项4
    private String answer;  //答案
    private String explains;//拓展知识
    private String url;     //图片url
}