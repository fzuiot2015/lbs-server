package edu.fzu.lbs.entity.po;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "check_step")
public class CheckStep {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer type;

    private Integer step;

    private String content;
}
