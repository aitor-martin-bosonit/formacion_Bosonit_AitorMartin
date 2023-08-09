package com.formacion.block7crudvalidationparte1.Repository;

import com.formacion.block7crudvalidationparte1.Entity.Person;
import com.formacion.block7crudvalidationparte1.Entity.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfesorRepository extends JpaRepository<Profesor, Integer> {

    Profesor findByPerson(Person person);
    // Method to find professors by name
    List<Profesor> findByPersonaName(String name);

}
