package com.formacion.block7crud.Controladores.dto;

import com.formacion.block7crud.Entity.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PersonOutputDto {

    int id;
    String name;
    String age;
    String population;

    public PersonOutputDto(Person person)
    {
        if (person==null)
            return;
        id=person.getId();
        name=person.getName();
        age=person.getAge();
        population=person.getPopulation();
    }
}
