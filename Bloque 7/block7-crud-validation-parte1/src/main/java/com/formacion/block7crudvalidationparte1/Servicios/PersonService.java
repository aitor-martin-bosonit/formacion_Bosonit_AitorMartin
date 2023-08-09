package com.formacion.block7crudvalidationparte1.Servicios;

import com.formacion.block7crudvalidationparte1.Entity.Person;
import com.formacion.block7crudvalidationparte1.Repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PersonService {

    Person findById(int id);
    Person findByUsuario(String usuario);
    List<Person> findAll();
    void save(Person person);
    void update(int id, Person person) throws Exception;
    void delete(int id);

}
