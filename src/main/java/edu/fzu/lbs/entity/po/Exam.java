package edu.fzu.lbs.entity.po;

import lombok.Data;

import javax.persistence.*;

/**
 * 驾照考题
 */
@Data
@Entity
@Table(name = "exam")
public class Exam {
    /**
     * 驾照考题id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 科目类别
     */
    private String subject;

    /**
     * 驾驶证类型
     */
    private String type;

    /**
     * 题目
     */
    private String question;

    /**
     * 选项1
     */
    private String item1;

    /**
     * 选项2
     */
    private String item2;

    /**
     * 选项3
     */
    private String item3;

    /**
     * 选项4
     */
    private String item4;

    /**
     * 答案
     */
    private String answer;

    /**
     * 拓展知识
     */
    private String explains;

    /**
     * 图片url
     */
    private String url;
}