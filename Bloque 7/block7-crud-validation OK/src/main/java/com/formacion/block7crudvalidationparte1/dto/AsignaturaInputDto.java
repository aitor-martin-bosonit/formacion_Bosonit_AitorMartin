package com.formacion.block7crudvalidationparte1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AsignaturaInputDto {

    private Integer idAsignatura;
    private String comments;
    private Date initialDate;
    private Date finishDate;

}
