package com.formacion.block7crud.Servicios;

import com.formacion.block7crud.Controladores.dto.PersonInputDto;
import com.formacion.block7crud.Controladores.dto.PersonOutputDto;
import com.formacion.block7crud.Entity.Person;

import java.util.List;

public interface PersonService {

    PersonOutputDto addPerson(PersonInputDto personInputDto);

    PersonOutputDto updatePerson(Integer id, PersonInputDto personInputDto);

    void deletePerson(Integer id);

    Person getPersonById(Integer id);

    List<Person> getPersonByName(String name);

    List<Person> getAllPersons();

}
