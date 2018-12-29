package edu.fzu.lbs.entity.param;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class ExamParam {
    @NotEmpty
    private String subject;
    @NotEmpty
    private String type;
}
