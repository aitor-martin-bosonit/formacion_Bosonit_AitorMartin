package com.formacion.block7crudvalidationparte1.dto;

import com.formacion.block7crudvalidationparte1.Entity.Person;
import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentInputDto {

    private Integer idStudent;
    private Integer idPerson;;
    private int numHoursWeek;
    private String comments;
    private Integer idProfesor;
    private String branch;

}
