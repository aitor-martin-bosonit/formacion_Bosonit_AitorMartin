package com.formacion.block7crud.Entity;

import com.formacion.block7crud.Controladores.dto.PersonInputDto;
import com.formacion.block7crud.Controladores.dto.PersonOutputDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String age;
    private String population;

    public Person(PersonInputDto personInputDto) {
        this.id = personInputDto.getId();
        this.name = personInputDto.getName();
        this.age = personInputDto.getAge();
        this.population = personInputDto.getPopulation();
    }


}

