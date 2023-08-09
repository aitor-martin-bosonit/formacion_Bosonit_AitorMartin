package com.formacion.block7crud.Controladores.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonInputDto {

    int id;
    String name;
    String age;
    String population;

}
