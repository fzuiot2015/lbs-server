package edu.fzu.lbs.entity.po;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tips")
public class Tips {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;
}
