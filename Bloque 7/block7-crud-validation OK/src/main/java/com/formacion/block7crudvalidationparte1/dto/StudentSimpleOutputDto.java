package com.formacion.block7crudvalidationparte1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentSimpleOutputDto {

    private Integer idStudent;
    private int numHoursWeek;
    private String comments;

}
