package com.formacion.block7crudvalidationparte1.Repository;

import com.formacion.block7crudvalidationparte1.Entity.ProfesorStudent;
import com.formacion.block7crudvalidationparte1.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfesorStudentRepository extends JpaRepository<ProfesorStudent, Integer> {

    void deleteByStudent(Student student);
    List<ProfesorStudent> findByStudent(Student student);

}
