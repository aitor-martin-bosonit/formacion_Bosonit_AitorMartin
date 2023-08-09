package com.formacion.block7crudvalidationparte1.Repository;

import com.formacion.block7crudvalidationparte1.Entity.Person;
import com.formacion.block7crudvalidationparte1.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    Student findByPerson(Person person);
    Optional<Student> findByIdStudent(Integer idStudent);

    List<Student> findByPerson_Name(String name);

    List<Student> findByPersonName(String name);

}
