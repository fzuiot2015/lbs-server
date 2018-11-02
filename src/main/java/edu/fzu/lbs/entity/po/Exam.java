package edu.fzu.lbs.entity.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

@ApiModel(description = "驾照考题")
@Data
@Entity
@Table(name = "exam")
public class Exam {
    @ApiModelProperty("ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty("科目类别")
    private String subject;

    @ApiModelProperty("驾驶证类型")
    private String type;

    @ApiModelProperty("题目")
    private String question;

    @ApiModelProperty("选项1")
    private String item1;

    @ApiModelProperty("选项2")
    private String item2;

    @ApiModelProperty("选项3")
    private String item3;

    @ApiModelProperty("选项4")
    private String item4;

    @ApiModelProperty("答案")
    private String answer;

    @ApiModelProperty("拓展知识")
    private String explains;

    @ApiModelProperty("图片url")
    private String url;
}