package com.formacion.block7crudvalidationparte1.Repository;

import com.formacion.block7crudvalidationparte1.Entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    Person findById(int usuario);
    Person findByUsuario(String usuario);

    List<Person> findAll();
}
