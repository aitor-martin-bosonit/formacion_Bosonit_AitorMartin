package com.formacion.block7crudvalidationparte1.Servicios;

import com.formacion.block7crudvalidationparte1.Entity.Person;

import java.util.List;

public interface PersonService {

    Person agregarPersona(Person person);
    Person searchById(Integer id);
    Person searchByUser(String usuario);
    List<Person> getAll();
    void deletePerson(Integer id);
    Person modifyPerson(int id, Person personaEntity);

}
