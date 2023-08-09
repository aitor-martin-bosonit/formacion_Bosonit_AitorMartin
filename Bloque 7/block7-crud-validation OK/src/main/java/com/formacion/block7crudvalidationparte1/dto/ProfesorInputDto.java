package com.formacion.block7crudvalidationparte1.dto;

import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfesorInputDto {

    private Integer idProfesor;
    private Integer idPerson;;
    private String comments;
    private String branch;

}
